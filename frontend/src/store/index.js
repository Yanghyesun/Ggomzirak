import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';
import axios from 'axios';
import jwt_decode from "jwt-decode";

const menuData = require('@/views/main/menu.json');

export default createStore({
  state: {
    activeMenu: 'home',
    menus: menuData,
    loginUser: '',
    searchType: '',
    searchWord:'',
  },
  getters: {
    getMenus(state) {
      return state.menus;
    },
    getActiveMenuIndex(state) {
      const keys = Object.keys(state.menus);
      return keys.findIndex(item => item === state.activeMenu);
    },
    getActiveMenu(state) {
      return state.activeMenu;
    },
    getSearchType(state) {
      return state.searchType;
    },
    getSearchWord(state) {
      return state.searchWord;
    }
  },
  mutations: {
    setMenuActive (state, index) {
      const keys = Object.keys(state.menus);
      state.activeMenu = keys[index];
      console.log(state.activeMenu);
    },
    setMenuActiveMenuName(state, menuName) {
      state.activeMenu = menuName;
      console.log(state.activeMenu);
    },
    setLoginUser(state, user) {
      state.loginUser = user;
    },
    setSearchType(state, type) {
      state.searchType = type;
    },
    setSearchWord(state, word) {
      state.searchWord = word;
    }
  },
  actions: {
    login({ commit }, user) {
      const formData = new FormData();
      formData.append('userid', user.userId);
      formData.append('password', user.password);

      axios.post('/user/login', formData, {headers: {"Content-Type" : "multipart/form-data"}})
        .then((response) => {
          localStorage.setItem('accessToken', response.data.auth_token.accessJwt);
          localStorage.setItem('refreshToken', response.data.auth_token.refreshJwt);
          const user = jwt_decode(response.data.auth_token.accessJwt);
          localStorage.setItem('userId', user.User.userid);
          localStorage.setItem('userImage', user.User.image);
          localStorage.setItem('id', user.User.id);

          window.location.href = "/";
          commit('setMenuActiveMenuName', 'home');
          commit('setLoginUser', user.User.name);
        }).catch((error) => {
          alert("아이디와 비밀번호를 한번 더 확인해주세요!");
          console.log(error);
        });
    },
    logout({commit}) {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      localStorage.removeItem('userId');
      localStorage.removeItem('id');
      localStorage.removeItem('userImage');

      commit('setLoginUser', '');
      commit('setMenuActiveMenuName', 'home');
    }
  },
  modules: {},
  plugins: [createPersistedState()]
});