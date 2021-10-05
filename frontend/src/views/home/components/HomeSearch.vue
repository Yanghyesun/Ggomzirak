<template>
  <div class="search">
    <div class="search-contents">
      <p class="search-title">어떤 생활 꿀팁을 찾으시나요?</p>
      <form>
        <div style="margin-left: -6px; margin-top: 32px; margin-bottom: 24px; display: flex">
          <p class="search-type" @click="setType('제목', 0)">제목</p>
          <p class="search-type" @click="setType('내용', 1)">내용</p>
          <p class="search-type" @click="setType('글쓴이', 2)">글쓴이</p>
          <p class="search-type" @click="setType('태그', 3)">태그</p>
        </div>
        <label class="search-label">
          <img src="@/assets/ic_search.png" class="search-img" />
          <input
            class="search-input"
            type="text"
            placeholder="기름때 제거"
            v-model="searchWord"
            v-on:keypress.enter="goSearch"
          />
        </label>
      </form>
    </div>
  </div>
</template>
<script>
import { useStore } from 'vuex';
import { mapMutations } from 'vuex';

export default {
  name: 'HomeSearch',
  data() {
    return {
      searchType: '제목',
      searchWord: '',
    };
  },
  mounted() {
    const types = document.getElementsByClassName('search-type');
    types[0].style.borderBottom = '2px solid white';
    types[0].style.color = 'white';
  },
  setup() {
    const store = useStore();
    const login = () => {
      store.commit('setMenuActiveMenuName', 'login');
    };

    const logout = () => {
      store.dispatch('logout');
      window.location.href = '/';
    };

    return { login, logout };
  },

  methods: {
    ...mapMutations(['setSearchType', 'setSearchWord']),
    setType(value, idx) {
      this.searchType = value;
      const types = document.getElementsByClassName('search-type');
      types.forEach((type) => {
        type.style.borderBottom = '0';
        type.style.color = '#ffffffaa';
      });
      types[idx].style.borderBottom = '2px solid white';
      types[idx].style.color = 'white';
    },
    goSearch() {
      this.setSearchType(this.searchType);
      this.setSearchWord(this.searchWord);
      this.$router.push('/tips');
      this.$store.commit("setMenuActiveMenuName", "tips");
    },
  },
};
</script>

<style scoped>
.search {
  height: 460px;
  background: linear-gradient(to bottom right, #f3ebc4, #bbad89);
}
.search-contents {
  width: 1250px;
  margin: 0 auto;
  padding-top: 40px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.search-title {
  margin-top: 15px;
  font-size: 38px;
  color: white;
  text-shadow: 0 0 10px #cccccc;
}
.search-label {
  margin: 20px 0;
  background-color: white;
  padding: 10px 30px;
  height: 48px;
  border-radius: 4px;
  box-shadow: rgb(0 0 0 / 10%) 0px 7px 15px -2px;
  display: flex;
  align-items: center;
}
.search-img {
  width: 24px;
  height: 22px;
}
.search-input {
  flex: 1 1 0%;
  border: 0;
  margin-left: 18px;
  font-size: 18px;
  background-color: transparent;
}
.search-type {
  color: #ffffffaa;
  font-size: 18px;
  cursor: pointer;
  margin: 0 12px;
  padding-bottom: 4px;
}
</style>
