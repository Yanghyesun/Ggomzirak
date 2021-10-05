<template>
  <div class="home">
    <HomeSearch />
    <div class="contents">
      <div class="tips-user">
        <BestTips :bestTips="bestTips" />
        <BestUser :bestUser="bestUser"/>
      </div>
      <BestShorts :bestShorts="bestShorts" :type="'shorts'" />
      <BestLives :bestLives="bestLives" :type="'lives'" />
    </div>
    <MainFooter />
  </div>
</template>

<script>
import HomeSearch from './components/HomeSearch.vue'
import BestTips from './components/BestTips.vue'
import BestShorts from './components/BestShorts.vue'
import BestUser from './components/BestUser.vue'
import BestLives from './components/BestLives.vue'
import MainFooter from '../main/components/MainFooter.vue'
import $axios from 'axios';
export default {
  name: 'Home',
  data() {
    return {
      search: '',
      bestTips: [],
      bestUser: [],
      bestShorts: [],
      bestLives: []
    }
  },
  components: {
    HomeSearch,
    BestTips,
    BestShorts,
    BestUser,
    BestLives,
    MainFooter
  },
  mounted() {
    //통신
    $axios.get('/main/tips')
    .then((response) => {
      this.bestTips = response.data;
    })
    .catch(() => {
      console.log("오류가 발생했습니다.");
    });

    $axios.get('/main/shorts')
    .then((response) => {
      this.bestShorts = response.data;
    })
    .catch(() => {
      console.log("오류가 발생했습니다.");
    });

    $axios.get('/main/live')
    .then((response) => {
      this.bestLives = response.data;
    })
    .catch(() => {
      console.log("오류가 발생했습니다.");
    });

    $axios.get('/main/users')
    .then((response) => {
        const users = response.data;
        if(users.length > 2) {
          this.bestUser = [
            {
              image: users[1].user.image,
              username: users[1].user.name,
              likes: users[1].likes,
              views: users[1].views,
              posts: users[1].posts,
              ranking: 2,
              size: 140,
              point: 1500,
              top: 130
            },
            {
              image: users[0].user.image,
              username: users[0].user.name,
              likes: users[0].likes,
              views: users[0].views,
              posts: users[0].posts,
              ranking: 1,
              size: 180,
              point: 2000,
              top: 40
            },
            {
              image: users[2].user.image,
              username: users[2].user.name,
              likes: users[2].likes,
              views: users[2].views,
              posts: users[2].posts,
              ranking: 3,
              size: 110,
              point: 1000,
              top: 160
            }
          ];
        } else {
          alert("유저 목록이 불러오지 않았습니다.");
        }
    })
    .catch(() => {
      console.log("오류가 발생했습니다.");
    });
  }
};
</script>

<style scoped>
    .home {
      margin-top: -80px;
    }
    .contents {
      width: 1250px;
      margin: 120px auto;
      font-size: 22px;
    }
    .tips-user {
      display: flex;
    }
    .best-tips {
      flex: 1;
    }
    .best-user {
      flex: 1;
    }
    .best-shorts {
      margin-top: 150px;
    }
    .best-lives {
      margin-top: 160px;
    }
</style>
