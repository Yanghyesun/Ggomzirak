<template>
  <Main />
</template>

<script>
import Main from './views/main/Main.vue';

export default {
  name: 'App',
  components: {
    Main,
  },
};
</script>

<style>
@font-face {
  font-family: 'S-CoreDream-3Light';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
* {
  margin: 0;
  padding: 0;
  font-family: 'S-CoreDream-3Light';
}
/* 링크줄 없애기 */
.deco-none {
  text-decoration: none;
  color: black;
}
/* 제목 아래 밑줄 긋기 */
.contents-title {
  position: relative;
}
.contents-title:after {
  background-color: #cfc098;
  content: '';
  height: 12px;
  position: absolute;
  left: 0;
  margin-left: -0.8rem;
  top: 65%;
  width: calc(100% + 1.8rem);
  z-index: -1;
}
input:focus {
  outline: none;
}
</style>
