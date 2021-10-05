<template>
  <div class="tipsmain">
    <div class="tipsheader">
      <div class="menu-item">
        <p style="margin-left: 0.8rem; font-size: 24px">
          <span class="contents-title">
            <router-link to="/userTips" class="deco-none">관심 꿀팁</router-link>
          </span>
        </p>
      </div>
      <div class="menu-item">
        <p style="margin-left: 2rem; font-size: 24px">
          <router-link to="/userShorts" class="deco-none">관심 Shorts</router-link>
        </p>
      </div>
    </div>
    <div class="tipslist">
      <TipsList :tipsList="tipsList" />
    </div>
    <p v-if="tipsList.length === 0">
      좋아요를 누른 꿀팁이 없습니다! 마음에 드는 꿀팁을 찾아보세요:)
    </p>
  </div>
</template>

<script>
import TipsList from './TipsList';
import $axios from 'axios';
import check from '@/common/check.js';

// import TipsCreate from './components/TipsCreate'
export default {
  name: 'TipsMain',
  data() {
    return {
      search: '',
      type: 'title',
      tipsList: [],
      count: 1,
      id: localStorage.getItem('id'),
    };
  },
  components: {
    TipsList,
  },
  watch: {},
  created() {
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
      .get('/user/likes/' + this.id, { headers: myheaders })
      .then((response) => {
        this.count++;
        this.tipsList = response.data.tipList;
      })
      .catch(() => {
        alert('다시 로그인해주세요!');
        this.$router.push('/login');
      });
  },
};
</script>

<style scoped>
/* for search icon */
@import '//netdna.bootstrapcdn.com/font-awesome/3.0/css/font-awesome.css';

.tipsmain {
  width: 1250px;
  margin: 65px auto;
}
/* .contents {
      margin: 52px 65px;
      font-size: 24px;
    } */
.tipsheader {
  display: flex;
}

/* .searchbar {
      display: flex;
      margin-left: auto;
      height: 40px;
      
  } */

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

.tips-create-btn {
  width: 80px;
  height: 80px;
  position: fixed;
  bottom: 42px;
  right: 42px;
}
.tips-create-btn:hover {
  cursor: pointer;
}
</style>
