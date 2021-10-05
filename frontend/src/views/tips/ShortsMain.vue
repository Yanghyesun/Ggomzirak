<template>
  <div class="shortsmain">
    <div class="shortsheader">
      <div class="menu-item">
          <p style="margin-left: 0.8rem; font-size: 24px;">
              <router-link to="/tips?page=1" class="deco-none">꿀팁</router-link>
          </p>
      </div>
      <div class="menu-item">
          <p style="margin-left: 2rem; font-size: 24px;">
              <span class="contents-title">
              <router-link to="/shorts?page=1" class="deco-none">Shorts</router-link>
              </span>
          </p>
      </div>
      <div style="flex: 1"></div>
      <form>
        <select name="category" class="category" v-model="type">
          <option value="title" selected>제목</option>
          <option value="user_id">작성자</option>
          <option value="contents">내용</option>
          <option value="tags">태그</option>
        </select>
        
        <input type="text" class="search-word" placeholder="꿀팁 shorts를 찾아보세요!" v-model="search"/>
      </form>
    </div>

    <!-- <div class="shortslist" id="infinite-list">
      <ShortsItem v-for="(shorts, idx) in shortsList" :key="idx" :shorts="shorts" style="cursor: pointer"/> -->
    <div class="shortslist" id="infinite-list">
      <ShortsList :shortsList="shortsList" />
    </div>      
    <InfiniteScroll @infinite-scroll="onScroll" :noResult="noResult"></InfiniteScroll>
    <router-link to="/shortscreate" class="shorts-create-btn">
      <img src="@/assets/ic_create_btn.png"  alt="">
    </router-link>
  </div>
  
</template>

<script>
import $axios from 'axios';
import ShortsList from './components/ShortsList.vue'
import InfiniteScroll from "infinite-loading-vue3";

export default {
  name: 'ShortsMain',
  data() {
    return {
      shortsList: [],
      type: 'title',
      search: '',
      noResult: false,
      page: this.$route.query.page
}
  },
  components: {
    ShortsList,
    InfiniteScroll,
  },
  watch: {
    search: function(val) {
      $axios.get('/shorts/search?key=' + this.type + '&word=' + encodeURI(val, "utf-8") + '&page=1')
      .then((response) => {
        this.shortsList = response.data;
        console.log(this.shortsList);
      }).catch((error) => {
        console.log(error);
      });
    }
  },
  created() {
    //통신
    // axios
    // .get("https://i5a406.p.ssafy.io/api/v1/shorts?page=1")
    // .then((response) => {
    //   console.log(response.data[0]);
    //   this.shortsList = response.data;
    // });
    // $axios
    // .get(`/shorts?page=` + this.page)
    // .then((response) => {
    //   if(response.data.length === 0) this.noResult = true;
    //   else{this.shortsList = response.data; this.page++}
    // }).catch((error) => {
    //   console.log(error);
    // });

    // tag 검색
    let key = this.$store.getters['getSearchType'];
    let word = this.$store.getters['getSearchWord'];
    if (word != null && word != '') {
      if (key == '제목') key = 'title';
      else if (key == '내용') key = 'contents';
      else if (key == '글쓴이') key = 'user_id';
      else if (key == '태그') key = 'tags';

      this.search = word;//여기에 저장
      $axios
        .get(
          '/shorts/search?key=' +
            key +
            '&word=' +
            encodeURI(word, 'utf-8') +
            '&page=1'
        )
        .then((response) => {
          if(response.data.length === 0) this.noResult = true;
          else{this.shortsList = response.data; this.page++}
          console.log(this.shortsList);
        })
        .catch((error) => {
          console.log(error);
        });
      this.$store.commit('setSearchType', '');
      this.$store.commit('setSearchWord', '');
    } else {
      $axios
        .get(`/shorts?page=${this.page}`)
        .then((response) => {
          if(response.data.length === 0) this.noResult = true;
          else{this.shortsList = response.data; this.page++}
        })
        .catch((error) => {
          console.log(error);
        });
    }

  },
  methods: {
    onScroll(){
      let key = this.$store.getters['getSearchType'];
      setTimeout(() => {
    if (this.search != null && this.search != '') {
      //search 저장이 되어있으면
        $axios.get('/shorts/search?key=' +
            key +
            '&word=' +
            encodeURI(this.search, 'utf-8') +
            '&page='+this.page)
        .then((response) => {
          if(response.data.length === 0) this.noResult = true;
          else{
            console.log(response.data);
            response.data.forEach((item) => {
              this.shortsList.push(item);
            });
            this.page++;
          }
        }).catch((error) => {
          console.log(error);
        });
    }
    else{
        $axios.get('/shorts?page='+this.page)
        .then((response) => {
          if(response.data.length === 0) this.noResult = true;
          else{
            console.log(response.data);
            response.data.forEach((item) => {
              this.shortsList.push(item);
            });
            this.page++;
          }
        }).catch((error) => {
          console.log(error);
        });
      }
      }, 500);
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

  /* .shortslist {
        margin-top: 47px;
        display: grid;
        grid-template-columns: repeat(4, calc(94% / 4));
        row-gap: 24px;
        column-gap: 24px;
  } */


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