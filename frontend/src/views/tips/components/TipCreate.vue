/* eslint-disable */
<template>
  <div class="tipcreatemain">
    <div class="nav">
      <div class="menu-item">
        <p>
          <span class="contents-title">
            <router-link to="/tipcreate" class="deco-none">꿀팁 작성</router-link>
          </span>
        </p>
      </div>
      <div class="menu-item">
        <p style="margin-left: 2rem">
          <router-link to="/shortscreate" class="deco-none">꿀팁 Shorts 작성</router-link>
        </p>
      </div>
    </div>
    <div class="tipcreate-title">
      <span class="subtitle"><span class="required">*</span>제목</span>
      <div class="contents">
        <input
          v-model="title"
          type="text"
          class="title"
          placeholder="제목을 입력해주세요."
          style="text-indent: 0.5em"
        />
      </div>
    </div>

    <div class="tipcreate-content">
      <span class="subtitle"><span class="required">*</span>내용</span>
      <div class="contents">
        <textarea
          v-model="content"
          rows="20"
          type="text"
          class="content"
          placeholder="내용을 입력해주세요."
          style="resize: none; text-indent: 0.5em"
        ></textarea>
      </div>
    </div>
    <ThumbnailBox
      @fileupload="thumbnailfileupload"
      style="display: flex; margin-top: 24px; align-items: center"
    />
    <ImageBox
      @fileupload="fileupload"
      style="display: flex; margin-top: 24px; align-items: center"
    />

    <div class="tipcreate-tag">
      <div class="subtitle" style="margin-bottom: 30px">
        <span class="required">*</span>
        태그
      </div>
      <div style="width: 100%">
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
        <p class="explain">
          enter키를 누르면 태그가 생성됩니다. | 최대 10개의 태그를 설정할 수 있습니다.
        </p>
      </div>
    </div>

    <div class="buttons">
      <button class="btn-submit" @click="createTip">등록하기</button>
      <router-link to="/tips?page=1">
        <button class="btn-cancel">취소</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import ThumbnailBox from '../../common/ThumbnailBox.vue';
import ImageBox from '../../common/ImageBox.vue';
import $axios from 'axios';
import check from '@/common/check.js';

export default {
  name: 'TipCreate',

  data: () => ({
    title: '',
    content: '',
    tag: '',
    tags: [],
    images: [],
    thumbnail: File,
  }),

  components: {
    ImageBox,
    ThumbnailBox,
  },
  methods: {
    fileupload(data) {
      this.images = data;
    },
    thumbnailfileupload(data) {
      this.thumbnail = data;
    },
    createTags() {
      if (this.title === '' || this.content === '') {
        alert('제목과 내용을 입력하세요');
      } else {
        const data = {
          contents: this.content,
          title: this.title,
        };
        $axios.post('/tag', data).then((response) => {
          response.data.forEach((item) => {
              let check = false;
              this.tags.forEach((t) => {
                if(t === item) check = true;
              })
              if(!check) this.tags.push(item);          
              });
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
    createTip() {
      const userId = localStorage.getItem('id');

      if (this.title === '' || this.content === '') {
        alert('제목과 내용, 썸네일, 태그를 입력하세요');
      } else {
        const formData = new FormData();

        formData.append('contents', this.content);
        formData.append('title', this.title);
        this.tags.forEach((tag) => {
          formData.append('tagList', tag);
        });

        this.images.forEach((img) => {
          formData.append('imageFiles', img);
        });

        formData.append('thumbnailFile', this.thumbnail);

        formData.append('userId', userId);

        let myheaders = {};
        if (check == 0) {
          myheaders = {
            'Content-Type': 'multipart/form-data',
            'jwt-auth-access-token': localStorage.getItem('accessToken'),
          };
        } else {
          myheaders = {
            'Content-Type': 'multipart/form-data',
            'jwt-auth-refresh-token': localStorage.getItem('refreshToken'),
          };
        }

        $axios
          .post('/tips/register', formData, { headers: myheaders })
          .then(() => {
            console.log('성공');
            alert('꿀팁 글 등록을 완료했습니다!');
            window.location.href = '/tips?page=1' ;
          })
          .catch(() => {
            alert('꿀팁 글 등록을 실패했습니다!');
            console.log('실패');
          });
      }
    },
  },
};
</script>

<style scoped>
/* for search icon */
/* @import "//netdna.bootstrapcdn.com/font-awesome/3.0/css/font-awesome.css"; */

.nav {
  display: flex;
  height: 45px;
  align-items: center;
  font-size: 24px;
  margin-right: 36px;
}

.tipcreatemain {
  width: 1250px;
  margin: 65px auto;
}
.subtitle {
  width: 80px;
  font-size: 16px;
}
.tipcreate-title {
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

.tipcreate-content {
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

.tipcreate-tag {
  display: flex;
  font-size: 12px;
  margin-top: 24px;
  width: 100%;
  align-items: center;
}

.btn-tag-create {
  border: 1px solid #cfc098;
  color: #cfc098;
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

.ti-tag {
  background-color: #cfc098;
}

.buttons {
  display: flex;
  justify-content: center;
  margin-top: 30px;
}

.btn-submit {
  background-color: #cfc098;
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
  color: #c3c3c3;
  font-size: 14px;
  margin-top: 8px;
}
</style>
