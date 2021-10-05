import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/home/Home.vue";
// import Main from "../views/lives/Main.vue";
import Lives from "@/views/lives/Lives.vue";
import QnA from "@/views/qna/QnA.vue";
import QnACreate from "@/views/qna/components/QnACreate.vue";
import TipsMain from "@/views/tips/TipsMain.vue";
import TipCreate from "@/views/tips/components/TipCreate.vue";
import TipDetail from '@/views/tips/components/TipDetail.vue'
import ShortsMain from "@/views/tips/ShortsMain.vue";
import ShortsCreate from "@/views/tips/components/ShortsCreate.vue";
import ShortsDetail from "@/views/tips/components/ShortsDetail.vue";
// import User from "../views/user/User.vue";
import SignUp from "@/views/user/Signup.vue";
import Login from "@/views/user/Login.vue";
import LivesTipDetail from "@/views/lives/components/LivesTipDetail.vue";
import LivesTipBroadcaster from "@/views/lives/components/LivesTipBroadcaster.vue";
import QnADetail from "@/views/qna/components/QnADetail.vue";
import UserTips from "@/views/user/UserTips.vue";
import UserShorts from "@/views/user/UserShorts.vue";
import MyTips from "@/views/user/MyTips.vue";
import MyShorts from "@/views/user/MyShorts.vue";
import SaveLive from "@/views/lives/components/SaveLive.vue";

const fullMenu = require('@/views/main/menu.json');
function makeRoutesFromMenu () {
  let routes = Object.keys(fullMenu).map((key) => {
    if (key === 'tips') {
      return { path: fullMenu[key].path, name: key, component: TipsMain };
    } else if (key === 'lives') {
      return { path: fullMenu[key].path, name: key, component: Lives };
    } else if (key === 'qna') {
      return { path: fullMenu[key].path, name: key, component: QnA };
    // } else if (key === 'login') {
    //   return { path: fullMenu[key].path, name: key, component: Login };
    } else {
      return null;
    }
  });

  // menu 자체에는 나오지 않는 페이지 라우터에 추가
  routes.push({
    path: '/',
    name: '홈',
    component: Home
  });

  routes.push({
    path: '/lives/:userid',
    name: 'LivesTipDetail',
    component: LivesTipDetail
  });

  routes.push({
    path: '/lives/broadcast/:userid',
    name: 'LivesTipBroadcaster',
    component: LivesTipBroadcaster
  });

  routes.push({
    path: '/qna/:id',
    name: 'QnADetail',
    component: QnADetail
  });

  routes.push({
    path: '/shorts',
    name: 'ShortsMain',
    component: ShortsMain
  });

  routes.push({
    path: '/tipcreate',
    name: 'Tipcreate',
    component: TipCreate
  });

  routes.push({
    path: '/tips/:id',
    name: 'TipDetail',
    component: TipDetail
  });

  routes.push({
    path: '/shortscreate',
    name: 'ShortsCreate',
    component: ShortsCreate
  });

  routes.push({
    path: '/shorts/:id',
    name: 'ShortsDetail',
    component: ShortsDetail
  });  
  
  routes.push({
    path: '/qnacreate',
    name: 'QnACreate',
    component: QnACreate
  });

  routes.push({
    path: '/Signup',
    name: 'SignUp',
    component: SignUp
  });

  routes.push({
    path: '/login',
    name: 'Login',
    component: Login
  });

  routes.push({
    path: '/userTips',
    name: 'UserTips',
    component: UserTips
  });

  routes.push({
    path: '/userShorts',
    name: 'UserShorts',
    component: UserShorts
  });

  routes.push({
    path: '/myTips',
    name: 'MyTips',
    component: MyTips
  });

  routes.push({
    path: '/myShorts',
    name: 'MyShorts',
    component: MyShorts
  });

  routes.push({
    path: '/lives/save/:id',
    name: 'SaveLive',
    component: SaveLive
  })
  return routes
}

const routes = makeRoutesFromMenu();

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

// const routes = [
//   {
//     path: "/",
//     name: "Home",
//     component: Home,
//   },
//   // {
//   //   path: "/main",
//   //   name: "Main",
//   //   component: Main,
//   // },
//   {
//     path: "/lives",
//     name: "Lives",
//     component: Lives,
//   },
//   {
//     path: "/qna",
//     name: "QnA",
//     component: QnA,
//   },
//   {
//     path: "/tips",
//     name: "TipsMain",
//     component: TipsMain,
//   },
//   {
//     path: "/shorts",
//     name: "ShortsMain",
//     component: ShortsMain,
//   },
//   // {
//   //   path: "/user",
//   //   name: "User",
//   //   component: User,
//   // },
//   {
//     path: "/login",
//     name: "Login",
//     component: Login,
//   },  
//   {
//     path: "/signup",
//     name: "Signup",
//     component: Signup,
//   },
// ];

export default router;
