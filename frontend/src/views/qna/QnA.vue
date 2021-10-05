<template>
  <div class="qna">
    <div class="qna-header">
      <p class="tab-title">
        <span class="contents-title" style="font-size: 24px">질문하기</span>
      </p>
      <div style="flex: 1"></div>
      <form>
        <select name="category" class="category" v-model="type">
          <option value="title" selected>제목</option>
          <option value="user_id">작성자</option>
          <option value="contents">내용</option>
          <option value="tags">태그</option>
        </select>
        <input
          type="text"
          class="search-word"
          placeholder="원하는 질문을 찾아드려요!"
          v-model="search"
        />
      </form>
    </div>
    <div class="qna-list" id="infinite-list">
      <QnAItem
        v-for="(qna, idx) in qnaList"
        :key="idx"
        :qna="qna"
        @click="goDetail(qna.id)"
        style="cursor: pointer"
      />
    </div>
    <InfiniteScroll @infinite-scroll="onScroll" :noResult="noResult"></InfiniteScroll>
    <p v-if="qnaList.length === 0">검색 결과가 없습니다:)</p>
    <router-link to="/qnacreate" class="create-btn">
      <img src="@/assets/ic_create_btn.png" />
    </router-link>
  </div>
</template>

<script>
import $axios from 'axios';
import QnAItem from './components/QnAItem.vue';
import InfiniteScroll from 'infinite-loading-vue3';
export default {
  name: 'QnA',
  components: {
    QnAItem,
    InfiniteScroll,
  },
  data() {
    return {
      qnaList: [],
      type: 'title',
      search: '',
      test: [],
      noResult: false,
      page: this.$route.query.page,
    };
  },
  watch: {
    search: function (val) {
      this.page = 1;
      $axios
        .get('/qna/search?key=' + this.type + '&word=' + encodeURI(val, 'utf-8') + '&page=1')
        .then((response) => {
          if (response.data.length === 0) this.noResult = true;
          else {
            this.qnaList = response.data;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    $axios
      .get('/qna?page=' + this.page)
      .then((response) => {
        if (response.data.length === 0) this.noResult = true;
        else {
          this.qnaList = response.data;
          this.page++;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    onScroll() {
      if (this.page === 1) this.page++;
      setTimeout(() => {
        if (this.search === '') {
          $axios
            .get('/qna?page=' + this.page)
            .then((response) => {
              if (response.data.length === 0) this.noResult = true;
              else {
                console.log(response.data);
                response.data.forEach((item) => {
                  this.qnaList.push(item);
                });
                this.page++;
              }
            })
            .catch((error) => {
              console.log(error);
            });
        } else {
          $axios
            .get(
              '/qna/search?key=' +
                this.type +
                '&word=' +
                encodeURI(this.search, 'utf-8') +
                '&page=' +
                this.page
            )
            .then((response) => {
              if (response.data.length === 0) this.noResult = true;
              else {
                console.log(response.data);
                response.data.forEach((item) => {
                  this.qnaList.push(item);
                });
                this.page++;
              }
            })
            .catch((error) => {
              console.log(error);
            });
        }
      }, 500);
    },
    goDetail(idx) {
      // this.$router.push('/qna/' + idx);
      window.location.href = '/qna/' + idx;
    },
  },
};
</script>

<style scoped>
.qna {
  width: 1250px;
  margin: 65px auto;
}
.qna-header {
  display: flex;
}
.tab-title {
  margin-left: 0.8rem;
}
.qna-list {
  margin-top: 47px;
  display: grid;
  grid-template-columns: repeat(4, calc(94% / 4));
  row-gap: 24px;
  column-gap: 24px;
}
.create-btn {
  width: 80px;
  height: 80px;
  position: fixed;
  bottom: 42px;
  right: 42px;
}
.create-btn:hover {
  cursor: pointer;
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
}
</style>
