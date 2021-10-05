<template>
  <div class="header">
    <div id="nav">
      <div class="left-header">
        <router-link to="/" class="tab-item deco-none" @click="selectHome"
          ><img src="@/assets/logo.png" :width="60"
        /></router-link>
      </div>
      <div style="flex: 1"></div>
      <div class="right-header">
        <div class="menu-item" v-for="(item, idx) in state.menuItems" :key="idx">
          <router-link :to="item.path+'?page=1'" class="tab-item deco-none" @click="select(idx)">{{
            item.title
          }}</router-link>
        </div>
        <div class="menu-item" v-if="user === ''">
          <router-link to="/login" class="tab-item deco-none" @click="login">로그인</router-link>
        </div>
        <div class="menu-item" v-if="user != ''">
          <router-link to="/userTips" class="tab-item deco-none" @click="selectOther('user')">{{ user }}님</router-link>
        </div>
        <div class="menu-item" v-if="user != ''">
          <router-link to="/" class="tab-item deco-none" @click="logout">로그아웃</router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'MainHeader',
  data() {
    return {
      user: this.$store.state.loginUser,
    };
  },
  mounted() {
    const menu = this.$store.getters['getActiveMenu'];
    console.log(this.$router.currentRoute);
    if (menu === 'home') {
      this.changeTabColor('white');
    } else {
      this.changeTabColor('#202020');
    }
  },
  computed: {
    menuActive() {
      return this.$store.getters['getActiveMenu'];
    },
  },
  watch: {
    menuActive(value) {
      if (value === 'home') {
        this.changeTabColor('white');
      } else {
        this.changeTabColor('#202020');
      }
    },
  },
  setup() {
    const store = useStore();
    const state = reactive({
      menuItems: computed(() => {
        const MenuItems = store.getters['getMenus'];
        let keys = Object.keys(MenuItems);
        let menuArray = [];
        for (let i = 0; i < keys.length; ++i) {
          let menuObject = {};
          menuObject.path = MenuItems[keys[i]].path;
          menuObject.title = MenuItems[keys[i]].name;
          menuArray.push(menuObject);
        }
        return menuArray;
      }),
      activeIndex: computed(() => store.getters['getActiveMenuIndex']),
    });

    const select = (param) => {
      store.commit('setMenuActive', param);
    };

    const selectHome = () => {
      store.commit('setMenuActiveMenuName', 'home');
    };

    const changeTabColor = (color) => {
      let tabs = document.getElementsByClassName('tab-item');
      tabs.forEach((tab) => {
        tab.style.color = color;
      });
    };

    const selectOther = (text) => {
      store.commit('setMenuActiveMenuName', text);
    };

    const login = () => {
      store.commit('setMenuActiveMenuName', 'login');
    };

    const logout = () => {
      store.dispatch('logout');
      window.location.href = '/';
    };

    return { state, select, selectHome, selectOther, changeTabColor, login, logout };
  },
};
</script>
<style scoped>
.header {
  background-color: white;
  box-shadow: 0 0 3px 3px #eeeeee;
}
#nav {
  display: flex;
  height: 65px;
  align-items: center;
  font-size: 16px;
}
.left-header {
  margin-left: 20px;
}
.right-header {
  display: flex;
}
.menu-item {
  margin: 0 35px;
}
</style>
