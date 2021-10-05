<template>
  <div class="qnacreatemain">
      <p style="margin-left: 0.8rem">
          <span class="contents-title" style="font-size: 24px;">질문 작성</span>
      </p>

      <div class="qnacreate-title">
        <div class="subtitle"><span class="required">*</span>제목</div>
        <div class="contents">
          <input v-model="title" type="text" class="title" placeholder="제목을 입력해주세요.">
        </div>
      </div>

      <div class="qnacreate-content">
        <div class="subtitle"><span class="required">*</span>내용</div>
        <div class="contents">
          <textarea v-model="content" rows="20" type="text" class="content" placeholder="내용을 입력해주세요." style="resize: none;"></textarea>
        </div>
      </div>

      <ImageBox @fileupload="fileupload" :required="false" style="display: flex; margin-top: 24px; align-items: center;" />

      <div class="qnacreate-tag">
        <div class="subtitle" style="margin-bottom:30px;">
          <span class="required">*</span>
          태그
        </div>
        <div style="width: 100%;">
          <!-- 태그 만드는 곳 -->
          <div style="display: flex; margin-bottom: 8px; flex-wrap: wrap;">
              <p class="tag-item" v-for="(t, idx) in tags" :key="idx">
                #{{t}}
                <img src="@/assets/ic_delete.png" style="margin-left: 4px" :width="10" @click="deleteTag(t)"/>
              </p>
          </div>
          <div class="contents" style="display: flex; align-items: center;">
            <input class="tag-input" type="text" v-on:keypress.enter="addTags" v-model="tag" placeholder="태그를 입력해주세요."/>
            <div class="btn-tag-create" @click="createTags">생성</div>
          </div>
          <p class="explain">enter키를 누르면 태그가 생성됩니다. | 최대 10개의 태그를 설정할 수 있습니다.</p>
        </div>
      </div>

      <div class="buttons" style="font-size: 16px">
        <button class="btn-submit" @click="createQuestion">
          등록하기
        </button>

        <router-link to="/qna">
          <button class="btn-cancel">취소</button>
        </router-link>

      </div>
  </div>
  
</template>


<script>
import check from '../../../common/check.js';
import ImageBox from '../../common/ImageBox.vue'
import axios from 'axios';
import $axios from 'axios';
export default {
  name: 'QnACreate',
  components: {
    ImageBox
  },
  data: () => ({
    files: [],
    title: '',
    content: '',
    tag: '',
    tags: [],
    images: []
  }),
  methods: {
    fileupload(data) {
      this.images = data;
    },
    createTags() {
      if(this.title === '' || this.content === '') {
        alert("제목과 내용을 입력하세요");
      } else {
        const data = {
            'contents' : this.content,
            'title' : this.title
        };
        $axios.post('/tag', data).then((response) => {
            response.data.forEach((item) => {
              let check = false;
              this.tags.forEach((t) => {
                if(t === item) check = true;
              })
              if(!check) this.tags.push(item);
            });
            console.log(this.tags);
        });
      }
    },
    addTags() {
      if(this.tag != '') {
        if(this.tag.length < 11) {
          let check = false;
          this.tags.forEach((item) => {
            if(item === this.tag) check = true;
          })
          if(!check) this.tags.push(this.tag);
          else {
            alert("중복된 태그를 추가했습니다.");
          }
          this.tag = '';
        }
      }
    },
    deleteTag(item) {
      this.tags.forEach((t, idx) => {
        if(t === item) {
          this.tags.splice(idx, 1);
        }
      });
    },
    createQuestion() {
      const userId = localStorage.getItem('id');
      if(userId === null) {
        alert("로그인을 먼저 해주세요!");
        return;
      }
      let myheaders = '';
      if (check() == 0) {
        myheaders = localStorage.getItem('accessToken');
      } else {
        myheaders = localStorage.getItem('refreshToken');
      }
      if(this.title === '' || this.content === '' || this.tags.lenght === 0) {
        alert("제목 및 내용, 태그를 모두 입력하세요");
      } else {
        const formData = new FormData();

        formData.append('contents', this.content);
        formData.append('title', this.title);
        this.tags.forEach((tag) => {
          formData.append('tagList', tag);
        });
        //이미지가 있을 때
        this.images.forEach((img) => {
          formData.append('imageFiles', img);
        });

        //이미지가 없을 때
        // if(this.images.length == 0) {
        //   formData.append('imageFiles', '');
        // }
        formData.append('userId', userId);
        
        axios.post('/qna/register', formData, {headers: {"Content-Type" : "multipart/form-data", "jwt-auth-access-token" : myheaders}})
        .then(() => {
            alert("질문 글 등록을 완료했습니다!");
            this.$router.push("/qna?page=1");
        }).catch(() => {
          console.log('실패');
        });
      }

    }
  }

}
</script>
<style scoped>
  .qnacreatemain {
    width: 1250px;
    margin: 65px auto;
  }
  .subtitle {
    width: 80px;
    font-size: 16px;
  }
  .qnacreate-title {
    display: flex;
    height: 45px;
    font-size: 12px;
    margin-top: 24px;
    align-items: center;
  }
  .contents {
    width: 100%;
  }

  .title {
    border: 1px solid #dedede;
    display: flex;
    border-radius: 8px;
    width: calc(100% - 24px);
    padding: 12px;
    font-size: 16px;
  }

  .qnacreate-content {
    display: flex;
    font-size: 12px;
    margin-top: 24px;
    align-items: center;
  }

  .content {
    border: 1px solid #dedede;
    display: flex;
    border-radius: 8px;
    width: calc(100% - 24px);
    padding: 12px;
    font-size: 16px;
}

  .tag-input {
    border: 1px solid #dedede;
    flex: 1;
    border-radius: 8px;
    padding: 12px;
    font-size: 16px;
  }

  .qnacreate-tag {
    display: flex;
    font-size: 12px;
    margin-top: 24px;
    width: 100%;
    align-items: center;
  }

  .btn-tag-create {
    border: 1px solid #CFC098;
    color: #CFC098;
    padding: 14px 28px;
    border-radius: 8px;
    margin-left: 8px;
    font-size: 16px;
    align-items: center;
  }


  .tag {
    flex: 1;
    height: 40px;
    border-radius: 8px;
  }

  .tag-item {
    margin-right: 8px;
    border: 1px solid #cfc098;
    border-radius: 16px;
    color: #cfc098;
    padding: 8px 16px;
    margin-bottom: 2px;
  }
  .vue-tags-input {
    max-width: 100%;
    font-size: 16px;
  }

  .buttons {
    display: flex;
    justify-content: center;
    margin-top: 30px;
  }

  .btn-submit {
    background-color:#cfc098;
    margin-left: 20px;
    color: white;
    padding: 14px 48px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    display: inline-block;
    font-size: 16px;
  }
  
  .btn-cancel {
    border: 1px solid #cfc098;
    background-color: white;
    margin-left: 20px;
    color: #cfc098;
    padding: 14px 28px;
    cursor: pointer;
    border-radius: 8px;
    display: inline-block;
    font-size: 16px;
  }

  .required {
    color: #cfc098;
    font-size: 18px;
    margin-right: 8px;
  }

  .explain {
    color: #C3C3C3;
    font-size: 14px;
    margin-top: 8px;
  }

</style>