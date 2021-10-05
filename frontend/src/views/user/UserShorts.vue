<template>
  <div class="user">
    <div class="profile">
      <p class="tab-title">
        <span class="contents-title">프로필</span>
      </p>
      <div class="info">
        <!--프로필 이미지-->
        <div class="img">
          <img :src="myInfo.image" class="profile-img" />
          <label class="icon">
            <img
              src="@/assets/ic_edit_profile.png"
              class="profile-img"
              style="width: 30px; height: 30px"
            />
            <input type="file" style="display: none" @change="onCreate" />
          </label>
        </div>

        <!-- <img src="@/assets/ic_create_btn.png" @click="changeProfile" /> -->
        <!--프로필 정보-->
        <div class="user-info">
          <div>
            <span>아이디</span>
            <span>{{ userId }}</span>
          </div>
          <div>
            <span>닉네임</span> <span>{{ myInfo.name }}</span>
          </div>
          <div>
            <span>포인트 </span>
            <span> {{ myInfo.points }} points</span>
          </div>
          <div class="router-button">
            <label class="white-beige-btn">
              내 꿀팁 보러가기
              <input style="display: none" @click="goMyTips" />
            </label>
            <label class="white-beige-btn">
              내 Shorts 보러가기
              <input style="display: none" @click="goMyShorts" />
            </label>
          </div>
        </div>
        <!--등급-->
        <div class="rank">
          <div style="margin-bottom: 50px">
            <div>등급</div>
          </div>
          <div style="margin-bottom: 20px">주부 {{ myInfo.level }} 단</div>
          <div style="padding-left: 40px">
            <img src="@/assets/level.png" />
          </div>
        </div>
      </div>
    </div>
    <div class="user-posts">
      <div class="user-tips">
        <ShortsMain />
      </div>
    </div>
  </div>
</template>

<script>
import $axios from 'axios';
import ShortsMain from './ShortsMain';
import check from '@/common/check.js';

export default {
  name: 'UserShorts',
  components: {
    ShortsMain,
  },
  data() {
    return {
      userId: localStorage.getItem('userId'),
      id: localStorage.getItem('id'),
      myInfo: {},
      myTips: [],
      myShorts: [],
    };
  },

  mounted() {
    //통신
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
      .get('/user/detail/' + this.userId, { headers: myheaders })
      .then((response) => {
        this.myInfo = response.data;
      })
      .catch(() => {
        alert('다시 로그인해주세요!');
        this.$router.push('/login');
      });
  },
  methods: {
    fileupload(image) {
      const data = new FormData();

      data.append('userId', this.userId);
      data.append('profile', image);

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

      $axios
        .put('/user/changeprofile', data, {
          headers: myheaders,
        })
        .then((response) => {
          console.log(response);
          this.getUserInfo();
        })
        .catch(() => {
          alert('다시 로그인해주세요!');
          this.$router.push('/login');
        });
    },
    getUserInfo() {
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
        .get('/user/detail/' + this.userId, { headers: myheaders })
        .then((response) => {
          this.myInfo = response.data;
        })
        .catch(() => {
          alert('다시 로그인해주세요!');
          this.$router.push('/login');
        });
    },
    onCreate(event) {
      const img = event.target.files[0];
      console.log(img);
      this.fileupload(img);
    },
    goMyShorts() {
      this.$router.push('/myShorts');
    },
    goMyTips() {
      this.$router.push('/myTips');
    },
  },
};
</script>

<style scoped>
.user {
  width: 1250px;
  margin: 65px auto;
}
.info {
  display: flex;
  width: 100%;
  align-items: center;
}
.img {
  flex: 0.5;
  position: relative;
}
.icon {
  position: absolute;
  right: 8px;
  bottom: 40px;
}
.user-info {
  flex: 1.7;
  margin: 0px 40px;
}
span {
  display: inline-block;
  margin: 17px 25px;
}
.rank {
  flex: 1.8;
}
.profile-img {
  margin-top: 30px;
  width: 240px;
  height: 240px;
  border-radius: 50%;
  background-color: gray;
}
.user-posts {
  display: flex;
  width: 100%;
  margin: 50px 0px;
}
.user-tips {
  flex: 1;
}
.user-shorts {
  flex: 1;
}
.white-beige-btn {
  border: 1px solid #cfc098;
  color: #cfc098;
  padding: 10px 20px;
  display: flex;
  align-items: center;
  border-radius: 8px;
  margin: 10px 10px;
}
.white-beige-btn:hover {
  cursor: pointer;
}
.router-button {
  display: flex;
}
.router-button > * {
  flex: 1;
}
</style>
