<template>
  <div class="shortsdetail">
    <p class="shortstitle" style="font-size: 32px">
      <span class="contents-title">{{ shorts.shorts.title }}</span>
    </p>
    <div class="contents">
      <div class="shorts-video">
        <div class="thumbnail">
          <!-- <img :src="shorts.shorts.thumbnail" class="thumbnail-img"/> -->
          <video controls height="576" class="shorts" :poster="shorts.shorts.thumbnail">
            <source :src="shorts.shorts.video" type="video/mp4" />
          </video>
        </div>
        <!-- <user-video
                    :stream-manager="subscribe"
                /> -->
        <div class="shorts-contents">
          <div class="shorts-content">
            <p style="font-size: 24px; margin-top: 24px">{{ shorts.shorts.contents }}</p>
            <div class="shorts-tags">
              <div class="tag" v-for="tag in shorts.tags" v-bind:key="tag" @click="goSearch(tag)">
                #{{ tag }}
              </div>
            </div>
            <div class="user-info">
              <img :src="shorts.shorts.user.image" class="user-profile" />
              <p style="font-size: 16px">{{ shorts.shorts.user.name }}</p>
            </div>
          </div>
          <div style="flex: 1"></div>
          <div class="shorts-info">
            <div class="shorts-views" style="width: 50px">
              <img src="@/assets/ic_fill_view.png" class="shorts-infographic" />
              <p style="font-size: 12px; text-align: center">
                {{ shorts.shorts.views }}
              </p>
            </div>
            <div class="shorts-likes" style="width: 50px">
              <img v-if="!isLike" src="@/assets/ic_fill_like.png" class="shorts-infographic" />
              <img
                v-if="isLike"
                src="@/assets/ic_like.png"
                class="shorts-infographic"
                @click="putLikes"
                style="cursor: pointer"
              />
              <p style="font-size: 12px; text-align: center">
                {{ shorts.shorts.likes }}
              </p>
            </div>
            <div class="shorts-coms" style="width: 50px">
              <img src="@/assets/ic_fill_comments.png" class="shorts-infographic" />
              <p style="font-size: 12px; text-align: center">
                {{ shorts.comments }}
              </p>
            </div>
            <div class="shorts-createdtime" style="width: 60px">
              <img src="@/assets/ic_watch.png" class="shorts-infographic" />
              <p style="font-size: 12px; text-align: center">
                {{ shorts.shorts.time.slice(0, 10) }}
                <!-- <p>2021.08.17</p> -->
              </p>
            </div>
          </div>
        </div>
        <div class="answer">
          <div class="info">
            <form>
              <div style="display: flex">
                <textarea
                  rows="1"
                  placeholder="답변을 달아주세요!"
                  class="comment-input"
                  v-model="commentsContent"
                ></textarea>
                <div class="create-btn" @click="commentsCreate">등록</div>
              </div>
            </form>
            <div class="comments-list">
              <p v-if="comments.length===0">댓글이 없네요! 댓글을 달아주세요 :)</p>
              <CommentItem v-for="(comment, idx) in comments" :key="idx" :comment="comment" />
            </div>
          </div>
        </div>
      </div>

      <div class="shorts-recommend">
        <p style="font-size: 20px; margin-bottom: 10px"><b>#다른_꿀팁_Shorts</b></p>
        <div class="shorts-recommend-list">
          <ShortsRecommendItem
            v-for="(shorts, idx) in shortsRecommendedList.slice(0, 6)"
            :key="idx"
            :shorts="shorts"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
// import axios from 'axios'
import $axios from 'axios';
import ShortsRecommendItem from './ShortsRecommendItem';
import CommentItem from './CommentItem.vue';
import { mapMutations } from 'vuex';
import check from '@/common/check.js';

export default {
  name: 'ShortsDetail',
  components: {
    CommentItem,
    ShortsRecommendItem,
  },
  data() {
    return {
      isLike: false,
      shorts: Object,
      shortsrecommendedlist: [],
      comments: [],
      commentsContent: '',
      idx: -1,
      page: 1,
      searchType: '태그',
      searchWord: '',
      shortsList: [],
    };
  },
  computed: {},

  setup() {},

  created() {
    // 통신
    // axios
    // .get(`https://i5a406.p.ssafy.io/api/v1/shorts/${this.$route.params.id}`,{
    //     params: {
    //         userId: this.$route.query.userId
    //     },
    // })
    // .then((response) => {
    //     console.log(this.$route.params.id);
    //     this.shorts = response.data;
    // });

    // axios
    // .get(`https://i5a406.p.ssafy.io/api/v1/shorts/comments/${this.$route.params.id}`,{
    //     params: {
    //         page: 1
    //     },
    // })
    // .then((response) => {
    //     console.log(response.data[0]);
    //     this.comments = response.data;
    // });

    $axios.get('/shorts?page=1').then((response) => {
      console.log(response.data[0]);
      this.shortsRecommendedList = response.data;
    });

    var userId = localStorage.getItem('id');
    if (userId == null) userId = 0; //로그인하지 않았을 때

    this.idx = this.$route.params.id;
    $axios
      .get(`shorts/${this.$route.params.id}`, {
        params: {
          userId: userId,
        },
      })
      .then((response) => {
        console.log(this.$route.params.id);
        this.isLike = response.data.likes; //좋아요누를수있는지
        this.shorts = response.data;
      });

    $axios
      .get(`/shorts/comments/${this.$route.params.id}`, {
        params: {
          page: 1,
        },
      })
      .then((response) => {
        // console.log(response.data[0]);
        this.comments = response.data;
      });
  },
  methods: {
    ...mapMutations(['setSearchType', 'setSearchWord']),
    // 좋아요
    putLikes() {
      const userId = localStorage.getItem('id');
      const user = {
        email: 'string',
        id: userId, //id만 입력하면됨
        image: 'string',
        level: 'string',
        name: 'string',
        points: 0,
        role: 'GUEST',
      };

      let myheaders = {};
      if (check() == 0) {
        myheaders = {
          'jwt-auth-access-token': localStorage.getItem('accessToken'),
        };
      } else {
        myheaders = {
          'jwt-auth-refresh-token': localStorage.getItem('refreshToken'),
        };
      }

      $axios
        .put(`/shorts/likes/${this.shorts.shorts.id}`, user, { headers: myheaders })
        .then((response) => {
          console.log(response);
          this.isLike = false;
          this.shorts.shorts.likes++;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // 댓글 입력
    commentsCreate() {
      const userId = localStorage.getItem('id');

      const data = {
        contents: this.commentsContent,
        postId: this.idx,
        typeId: 2,
        userId: userId,
      };

      console.log(data);

      let myheaders = {};
      if (check() == 0) {
        myheaders = {
          'jwt-auth-access-token': localStorage.getItem('accessToken'),
        };
      } else {
        myheaders = {
          'jwt-auth-refresh-token': localStorage.getItem('refreshToken'),
        };
      }

      $axios
        .put('/shorts/comments/register', data, { headers: myheaders })
        .then(() => {
          this.commentsContent = '';
          this.getCommentList();
        })
        .catch(() => {
          alert('답변 등록에 오류가 발생했습니다.');
        });
    },

    // commentlist 가져오기
    getCommentList() {
      $axios
        .get('/shorts/comments/' + this.idx + '?page=1')
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
      window.location.href = '/shorts?page=1';
    },
  },
};
</script>
<style scoped>
.shortsdetail {
  margin: 38px 65px;
  /* flex-direction: row; */
}
.contents {
  height: 100%;
  display: flex;
  margin-top: 24px;
  /* flex-direction: row; */
}
.shorts-video {
  flex: 2;
  margin-right: 24px;
  border-radius: 8px;
  /* margin-left: 36px; */
}
.shorts-view {
  width: 100%;
  background-color: gray;
  border-radius: 8px;
}
.shorts-recommend {
  flex: 0.6;
  height: 750px;
  /* background-color: #F4F4F4; */
  border-radius: 8px;
  /* padding: 16px 24px; */
  width: 300px;

  /* display: flex;
        flex-basis: 20%;
        flex-direction: row; */
}
.shorts-contents {
  display: flex;
  width: 1024px;
}

.shorts-recommend-list {
  display: grid;
  row-gap: 10px;
}

.shorts-infographic {
  width: 32px;
  height: 32px;
  display: block;
  margin-bottom: 5px;
  margin-right: auto;
  margin-left: auto;
}

.shorts-info {
  display: flex;
  align-items: center;
}
.user-profile {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 8px;
}
.user-info {
  display: flex;
  align-items: center;
  margin-top: 20px;
}
.thumbnail {
  height: 576px;
  width: 1024px;
  position: relative;
  background-color: rgba(0, 0, 0, 1);
  border-radius: 16px;
  display: flex;
  flex-basis: 70%;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  background-size: cover;
}
.thumbnail-img {
  height: auto;
  max-height: 576px;
  width: auto;
  /* max-width: 960px; */
  position: relative;
  background-color: rgba(0, 0, 0, 1);
  display: flex;
  flex-basis: 70%;
  justify-content: center;
  align-items: center;
}
/* .thumbnail:after {
        content : "";
        display: block;
        position: absolute;
        border-radius: 16px;
        top: 0;
        left: 0;
        background-image: url('https://m.young.hyundai.com:444/upload/CMS_NEWS_IMAGE/2017/12/01/CMS_NEWS_IMAGE_POd5lTOs2I0ydzAL9c5w.jpg');
        background-size: cover;
        width: 100%;
        height: 100%;
        z-index: -1;
    } */
/* .play-btn {
        
    } */
.play-btn:hover {
  cursor: pointer;
}
  .shorts-tags {
    display: flex;
    margin-top: 26px;
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

.answer {
  width: 960px;
  margin: 32px auto;
}
.comment-input {
  flex: 1;
  padding: 12px 0;
  border: 1px solid #dedede;
  border-radius: 8px 0 0 8px;
  text-indent: 1em;
}
.create-btn {
  background-color: #cfc098;
  padding: 10px 15px;
  color: white;
  border-radius: 0 8px 8px 0;
  cursor: pointer;
}

.comments-list {
  margin-top: 24px;
}
</style>
