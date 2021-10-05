<template>
  <div class="card">
    <!-- <img :src="shorts.url" class="shorts-img" style="width: 100%" > -->
    <!-- <img :src="shorts.thumbnail" class="shorts-img" @click="goDetail" style="cursor: pointer;"> -->
    <img :src="shorts.thumbnail" class="shorts-img" @click="goDetail(shorts.id)" style="cursor: pointer;">
    <div class="container">
      <!-- {{shorts.id}} -->
      <p class="shorts-title" :title="shorts.title"> {{shorts.title}}</p>
      <p class="shorts-content">{{shorts.contents}}</p>
      <div class="shorts-list">
        <p class="shorts-tags" v-for="(tags,idx) in shorts.tags" :key ="idx" :tags="tags">
          #{{tags}}
        </p>
      </div>
      <div class="shorts-profile">
        <img class="profile-img" :src="shorts.user.image" alt="profile_basic" style="width: 35px; margin-right: 12px">
        <span>
          {{shorts.user.name}}
        </span>
            <div style="flex: 1"></div>

        <div class="shorts-views" style="display: flex">
            <img src="@/assets/ic_view.png" :width="18" :height="18" style="margin-right: 8px">
            <span>
                {{shorts.views}}
            </span>
        </div>
        <div class="shorts-likes" style="display: flex">
            <img src="@/assets/ic_like.png" :width="21" :height="18" style="margin-right: 8px">
            <span>
                {{shorts.likes}}
            </span>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
// import {useRouter} from 'vue-router'
// import $axios from 'axios'

export default {
  name: 'ShortsItem',
  props: {
    shorts: Object,
    tagList: []
  },
  created(){
  },
  // setup(props) {
  //   const router = useRouter()

  //   const goDetail = ()  => {
  //       router.push({
  //           name: 'ShortsDetail',
  //           params: {
  //               id: props.shorts.id
  //           },
  //           query: { userId: props.shorts.user.id }
  //           //사용자 id
  //       })
  //   }

  //   return { 
  //     goDetail,
  //     }
  methods: {
      goDetail(shortsId) {
          window.location.href = '/shorts/' + shortsId + '?userId=' + localStorage.getItem('id');
      }  
},
}
</script>

<style scoped>
.card {
  display:inline-block;
  box-shadow: 0 8px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 290px;/* 크기 고정 */
  border-radius: 20px;
  min-width: 0px;
  margin: 0px 0px 24px 0px;
  height: auto;
}
/* 카드 간격 다르게 */
/* .card:nth-child(8n+1), .card:nth-child(8n+3), .card:nth-child(8n+6), .card:nth-child(8n+8){
  height: 400px;
}
.card:nth-child(8n+2), .card:nth-child(8n+4), .card:nth-child(8n+5), .card:nth-child(8n+7){
  height: 445px;
} */

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

/* 사진 크기 다르게
.card:nth-child(8n+1) > .tip-img, .card:nth-child(8n+3) > .tip-img, .card:nth-child(8n+6) > .tip-img, .card:nth-child(8n+8) > .tip-img{
  border-radius: 20px 20px 0 0;
  width: 100%;
  height: 55%;
  object-fit: cover;
}
.card:nth-child(8n+2) > .tip-img, .card:nth-child(8n+4) > .tip-img, .card:nth-child(8n+5) > .tip-img, .card:nth-child(8n+7) > .tip-img{
  border-radius: 20px 20px 0 0;
  width: 100%;
  height: 58%;
  object-fit: cover;
} */

.shorts-img{
  border-radius: 20px 20px 0 0;
  width: 100%;
}

.profile-img{
  border-radius: 20px;
}

.container {
  padding: 18px 18px 0 18px;
  overflow: hidden;
  
}

.shorts-title {
  font-size: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  /* margin-bottom: 6px; */
}

.shorts-content {
  color: #dedede;
  font-size: 14px;
  margin: 10px 0px;
  overflow:hidden;
  text-overflow: ellipsis;
  white-space: normal;
  display:-webkit-box;
  -webkit-line-clamp:2;
  -webkit-box-orient:vertical
}

.shorts-list{
  display: flex;
  flex-flow: row wrap;
  overflow: hidden;
  height: 38px;
}

.shorts-tags {
  background-color: #CFC098; 
  border: none;
  color: white;
  padding: 5px 12px;
  text-align: center;
  font-size: 12px;
  margin: 2px 8px 10px 0px;
  cursor: pointer;
  border-radius: 16px;
}

.shorts-profile {
  font-size: 16px;
  display: flex;
  margin-top: 10px;
  align-items: center;
  margin-bottom: 20px;
}

.shorts-likes {
  margin-left: 10px;
}
</style>