<template>
  <div class="tipdetail">
    <!-- header -->
    <div class="tip-header">
      <img :src="tip.tips.thumbnail" class="thumbnail-img"/>
      <div class="header-info">
        <div style="width: 900px; margin: 200px auto 0 auto;">
            <p class="tip-title">{{tip.tips.title}}</p>
            <div>
              <img :src="tip.tips.user.image" alt="profile_basic" style="width: 40px; margin-right: 10px " class="profile-img">
              <div class="tip-profile">
                <p style="font-size: 12px; margin-bottom: 5px">
                  {{tip.tips.user.name}}
                </p>
                <p style="font-size: 12px">
                  {{tip.tips.time}}
                </p>
              </div>
            </div>
        </div>
      </div>
    </div>
    <!-- contents -->
    <div class="tip-detail-contents">
      <div class="tip-content">
        {{tip.tips.contents}}
      </div>
      <div class="images">
        <img :src="image" v-for="image in tip.images" v-bind:key="image" class="tips-img">
      </div>
      <div class="tip-tags">
        <div class="tag" v-for="tag in tip.tags" v-bind:key="tag" @click="goSearch(tag)">
          #{{tag}}
        </div>
      </div>
      <div class="tip-likes-coms">
        <div class="tip-like">
            <img v-if="!isLike" src="@/assets/ic_fill_like.png" style="width: 18px; margin-right: 3px">
            <img v-if="isLike" src="@/assets/ic_like.png" style="width: 18px; margin-right: 3px" @click="putLikes">
            <span style="font-size: 20px">
                {{tip.tips.likes}}
            </span>
        </div>
        <div class="tip-com">
            <img src="@/assets/ic_comments.png" style="width: 20px; margin-right: 3px">
            <span style="font-size: 20px">
                {{tip.comments}}
            </span>
        </div>
      </div>
      <div class="answer">
        <form>
          <div style="display: flex;">
              <textarea rows="1" placeholder="답변을 달아주세요!" class="comment-input" v-model="commentsContent"></textarea>
              <div class="create-btn" @click="commentsCreate">등록</div>
          </div>
      </form>
      <div class="comments-list">
          <p v-if="comments.length===0">댓글이 없네요! 댓글을 달아주세요 :)</p>
          <CommentItem v-for="(comment, idx) in comments" :key="idx" :comment="comment"/>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommentItem from './CommentItem.vue'
import $axios from 'axios';
import { mapMutations } from 'vuex';
import check from '@/common/check.js';

export default {
  name: 'TipDetail',
  props: {
  },
  components: {
    CommentItem
  },
  data() {
    return {
        isLike:false,
        tip: Object,
        comments: [],
        commentsContent: '',
        idx: -1,
        page: 1,
        searchType: '태그',
        searchWord: '',
    }
  },
  methods: {
    ...mapMutations(['setSearchType', 'setSearchWord']),
    putLikes(){
      const userId = localStorage.getItem('id');
      const user ={
          "email": "string",
          "id": userId,//id만 입력하면됨
          "image": "string",
          "level": "string",
          "name": "string",
          "points": 0,
          "role": "GUEST"
      }

      let myheaders = {};
        if (check == 0) {
          myheaders = {
            'jwt-auth-access-token': localStorage.getItem('accessToken'),
          };
        } else {
          myheaders = {
            'jwt-auth-refresh-token': localStorage.getItem('refreshToken'),
          };
        }

      $axios.put(`/tips/likes/${this.tip.tips.id}`,user, { headers: myheaders })
      .then((response) => {
          console.log(response);
          this.isLike=false;
          this.tip.tips.likes++;
      })
      .catch((error) => {
            console.log(error);
      });
    },
    commentsCreate() {
        const userId = localStorage.getItem('id');

        const data = {
            'contents' : this.commentsContent,
            'postId' : this.idx,
            'typeId' : 1,
            'userId' : userId, 
        };

        let myheaders = {};
        if (check == 0) {
          myheaders = {
            'jwt-auth-access-token': localStorage.getItem('accessToken'),
          };
        } else {
          myheaders = {
            'jwt-auth-refresh-token': localStorage.getItem('refreshToken'),
          };
        }

        $axios.post('/tips/comments/register', data, { headers: myheaders })
        .then(() => {
            // alert("답변 감사합니다 :)");
            this.commentsContent = '';
            this.getCommentList();
        })
        .catch(() => {
            alert("답변 등록에 오류가 발생했습니다.");
        });
    },
    getCommentList() {
        $axios.get('/tips/comments/' + this.idx + '?page=1')
        .then((response) => {
            this.comments = response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    },
    goSearch(tag) {
      this.searchWord = tag;
      this.setSearchType(this.searchType);
      this.setSearchWord(this.searchWord);
      window.location.href = '/tips?page=1';
    },
  },
   setup() {
   },
  created() {
    var userId = localStorage.getItem('id');
    if(userId==null)
      userId=0;//로그인하지 않았을 때

    this.idx = this.$route.params.id;
    $axios
    .get('/tips/' + this.idx,{
      params:{
        userId: userId
      },
    })
    .then((response) => {
      this.isLike=response.data.likes;//좋아요누를수있는지
      this.tip = response.data;
    });
    $axios
    .get('/tips/comments/' + this.idx + '?page='+this.page)
    .then((response) => {
      // console.log(response.data[0]);
      this.comments = response.data;
    });
  }
}
</script>

<style scoped>


.tipdetail {
    display: flex;
    flex-direction: column;
    margin-bottom: 65px;
}

  
  .tip-header {
    font-size: 20px;
    height: 400px;
    width: 100%;
    position: relative;
  }

  .thumbnail-img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    opacity: 0.4;
  }

  .tip-title {
    font-size: 36px;
    margin-bottom: 20px;
  }

  .header-info {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
  }

  .tip-detail-contents {
    width: 900px;
    margin: 32px auto;
  }

  .tip-content {
    padding: 60px 0;
    white-space: pre;
  }

  .images {
    display: flex;
    height: 230px;
    flex-flow: row wrap;
    overflow-x:auto ;
    -ms-overflow-style: none
  }
  ::-webkit-scrollbar { 
    display:none; 
  }

  .tips-img {
    border-radius: 8px;
    width: 280px;
    height: 200px;
    margin: 0px 18px 18px 0px;
    object-fit: cover;
  }

  .tip-tags {
    display: flex;
    margin-top: 36px;
    flex-wrap: nowrap;
  }

  .tag {
    border: 1px solid #CFC098;
    color: #CFC098;
    border-radius: 16px;
    padding: 8px 16px 5px 16px;
    font-size: 14px;
    margin-right: 10px;
    display:block;
    white-space: nowrap;
  }
  .tip-likes-coms {
    margin-top: 24px;
  }

  .tip-like {
    display: inline-block;
    font-size: 16px;
    margin-right: 16px;
  }
  .tip-com {
    display: inline-block;
    font-size: 16px;
  }

  .profile-img{
  border-radius: 20px;
}

  .tip-profile {
    display: inline-block;
  }

  .tip-like {
    display: inline-block;
    font-size: 16px;
    margin-right: 8px;
  }
  .tip-com {
    display: inline-block;
    font-size: 16px;
  }

  .answer {
      margin: 32px auto;
  }
  .comment-input {
      flex: 1;
      padding: 12px 0;
      border: 1px solid #DEDEDE;
      border-radius: 8px 0 0 8px;
  }
  .create-btn {
      background-color: #CFC098;
      padding: 10px 15px;
      color: white;
      border-radius: 0 8px 8px 0;
  }

  .comments-list {
    margin-top: 24px;
  }
</style>
