<template>
  <div class="shortsmain">
    <div class="shortsheader">
      <div class="menu-item">
        <p style="margin-left: 0.8rem; font-size: 24px">
          <router-link to="/myTips" class="deco-none"> 내 꿀팁</router-link>
        </p>
      </div>
      <div class="menu-item">
        <p style="margin-left: 2rem; font-size: 24px">
          <span class="contents-title">
            <router-link to="/myShorts" class="deco-none"> 내 Shorts</router-link>
          </span>
        </p>
      </div>
    </div>

    <div class="shortslist" id="infinite-list">
      <ShortsItem
        v-for="(shorts, idx) in shortsList"
        :key="idx"
        :shorts="shorts"
        style="cursor: pointer"
      />
    </div>
    <p v-if="shortsList.length === 0">등록된 shorts가 없습니다! 멋진 shorts를 공유해주세요!</p>
  </div>
</template>

<script>
import $axios from 'axios';
import ShortsItem from './ShortsItem.vue';
import check from '@/common/check.js';

export default {
  name: 'MyShorts',
  data() {
    return {
      shortsList: [],
      type: 'title',
      search: '',
      count: 1,
      id: localStorage.getItem('id'),
    };
  },
  components: {
    ShortsItem,
  },
  created() {
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
      .get('/user/shorts/' + this.id, { headers: myheaders })
      .then((response) => {
        this.shortsList = response.data;
      })
      .catch(() => {
        alert('다시 로그인해주세요!');
        this.$router.push('/login');
      });
  },
  methods: {
    goDetail(idx) {
      window.location.href = '/shorts/' + idx;
    },
  },
};
</script>

<style scoped>
/* for search icon */
/* @import "//netdna.bootstrapcdn.com/font-awesome/3.0/css/font-awesome.css"; */

.shortsmain {
  width: 1250px;
  margin: 65px auto;
}
.shortsheader {
  display: flex;
}

.category {
  padding: 10px 3px 10px 8px;
  margin-right: 12px;
  border-radius: 5px;
  font-size: 14px;
  border: 1px solid #dacca6;
}
.search-word {
  padding: 10px 3px 10px 6px;
  border-radius: 5px;
  border: 1px solid #dacca6;
  width: 300px;
  font-size: 14px;
  text-indent: 0.5em;
}

.shortslist {
  margin-top: 47px;
  display: grid;
  grid-template-columns: repeat(4, calc(94% / 4));
  row-gap: 24px;
  column-gap: 24px;
}

.shorts-create-btn {
  width: 80px;
  height: 80px;
  position: fixed;
  bottom: 42px;
  right: 42px;
}
.shorts-create-btn:hover {
  cursor: pointer;
}
</style>
