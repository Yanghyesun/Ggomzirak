<template>
  <div class="shortscreatemain">
    <div class="nav">
      <div class="menu-item">
        <p>
          <router-link to="/tipcreate" class="deco-none">꿀팁 작성</router-link>
        </p>
      </div>
      <div class="menu-item">
        <p style="margin-left: 2rem">
          <span class="contents-title">
            <router-link to="/shortscreate" class="deco-none">꿀팁 Shorts 작성</router-link>
          </span>
        </p>
      </div>
    </div>
    <div class="shortscreate-title">
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

    <div class="shortscreate-content">
      <span class="subtitle"><span class="required">*</span>내용</span>
      <div class="contents">
        <textarea
          v-model="content"
          type="text"
          class="content"
          placeholder="내용을 입력해주세요."
          style="resize: none; text-indent: 0.5em"
        ></textarea>
      </div>
    </div>
    <!-- <input type="file" class="thumbnail">
        <button class="btn-thumbnail-attachment">첨부</button> -->
    <ThumbnailBox
      @fileupload="thumbnailfileupload"
      style="display: flex; margin-top: 24px; align-items: center"
    />
    <VideoBox
      @videoupload="videoupload"
      style="display: flex; margin-top: 24px; align-items: center"
    />

    <div class="shortscreate-tag">
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
      <!-- 등록 -->
      <button class="btn-submit" @click="createShorts">등록하기</button>
      <!-- 돌아가기 버튼 -->
      <router-link to="/shorts?page=1">
        <button class="btn-cancel">취소</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import ThumbnailBox from '../../common/ThumbnailBox.vue';
import VideoBox from '../../common/VideoBox.vue';
import axios from 'axios';
import $axios from 'axios';
import check from '@/common/check.js';

export default {
  name: 'ShortsCreate',

  data: () => ({
    title: '',
    content: '',
    tag: '',
    tags: [],
    thumbnail: '',
    video: '',
  }),

  components: {
    ThumbnailBox,
    VideoBox,
  },

  methods: {
    thumbnailfileupload(data) {
      this.thumbnail = data;
    },
    videoupload(data) {
      // this.images = data;
      this.video = data;
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
    createShorts() {
      console.log(this.title);
      console.log(this.content);
      console.log(this.video);
      console.log(this.thumbnail);
      if (this.title === '' || this.content === '' || this.video == '' || this.thumbnail == '') {
        alert('제목과 내용, 비디오, 이미지, 태그를 입력하세요');
      } else {
        const formData = new FormData();

        formData.append('contents', this.content);
        formData.append('title', this.title);
        this.tags.forEach((tag) => {
          formData.append('tagList', tag);
        });
        //이미지가 있을 때
        formData.append('thumbnailFile', this.thumbnail);

        //동영상이 있을 때
        // this.video.forEach((vd) => {
        formData.append('videoFile', this.video);
        // });

        //이미지가 없을 때
        // if(this.images.length == 0) {
        //   formData.append('imageFiles', '');
        // }
        formData.append('userId', localStorage.getItem('id'));

        let myheaders = {};
        if (check() == 0) {
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
        axios
          .post('/shorts/register', formData, {
            headers: myheaders,
          })
          .then(() => {
            console.log('성공');
            this.$router.push('/shorts?page=1');
          })
          .catch(() => {
            console.log('실패');
          });
      }
    },
  },
};
</script>

<style scoped>
/* for search icon */
@import '//netdna.bootstrapcdn.com/font-awesome/3.0/css/font-awesome.css';

.shortscreatemain {
  width: 1250px;
  margin: 65px auto;
}
/* .contents {
      margin: 52px 65px;
      font-size: 24px;
    } */
.nav {
  display: flex;
  height: 45px;
  align-items: center;
  font-size: 24px;
  margin-right: 36px;
}

.shortscreatemain {
  width: 1250px;
  margin: 65px auto;
}
.subtitle {
  width: 80px;
  font-size: 16px;
}
.shortscreate-title {
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
  height: 40px;
  border-radius: 8px;
  width: 100%;
}

.shortscreate-content {
  display: flex;
  height: 100px;
  font-size: 12px;
  margin-top: 30px;
  align-items: center;
}

.content {
  border: 1px solid #dedede;
  display: flex;
  height: 100px;
  border-radius: 8px;
  width: 100%;
}

.tag-input {
  border: 1px solid #dedede;
  flex: 1;
  border-radius: 8px;
  padding: 12px;
  font-size: 16px;
}

.shortscreate-tag {
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
  font-size: 12px;
  color: white;
  height: 40px;
  width: 160px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  display: inline-block;
}

.btn-cancel {
  border: 1px solid #cfc098;
  background-color: white;
  margin-left: 20px;
  font-size: 12px;
  color: #cfc098;
  height: 40px;
  width: 160px;
  cursor: pointer;
  border-radius: 8px;
  display: inline-block;
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
