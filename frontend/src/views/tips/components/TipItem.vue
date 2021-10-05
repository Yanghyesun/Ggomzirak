<template>
  <div class="card">
    <img :src="tip.thumbnail" class="tip-img">

    <div class="container">
      <p class="tip-title" :title="tip.title"> {{tip.title}}</p>
      <p class="tip-content">{{tip.contents}}</p>
      <div class="tip-list">
        <p class="tip-tags" v-for="(tags,idx) in tip.tags" :key ="idx" :tags="tags">
          #{{tags}}
        </p>
      </div>
      <div class="tip-profile">
        <img class="profile-img" :src="tip.user.image" alt="profile_basic" style="width: 35px; margin-right: 12px">
        <span>
          {{tip.user.name}}
        </span>
            <div style="flex: 1"></div>

        <div class="tip-like" style="display: flex">
            <img src="@/assets/ic_like.png" :width="19" :height="19" style="margin: 2px 8px 0px 0px;">
            <span>
                {{tip.likes}}
            </span>
        </div>
        <div class="tip-commments" style="display: flex">
            <img src="@/assets/ic_comments.png" :width="19" :height="19" style="margin: 2px 8px 0px 0px;">
            <span>
                {{tip.comments}}
            </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import {useRouter} from 'vue-router'

export default {
  name: 'TipItem',
  props: {
    tip: Object,
    tagList: []
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
  cursor: pointer;
}
/* 카드 간격 다르게 */
.card:nth-child(8n+1), .card:nth-child(8n+3), .card:nth-child(8n+6), .card:nth-child(8n+8){
  height: 440px;
}
.card:nth-child(8n+2), .card:nth-child(8n+4), .card:nth-child(8n+5), .card:nth-child(8n+7){
  height: 485px;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

/* 사진 크기 다르게 */
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
}

.profile-img{
  border-radius: 20px;
}

.container {
  padding: 18px 18px 0 18px;
  overflow: hidden;
}

.tip-title {
  font-size: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.tip-content {
  color: #c3c3c3;
  font-size: 14px;
  margin: 10px 0px;
  overflow:hidden;
  text-overflow: ellipsis;
  white-space: normal;
  display:-webkit-box;
  -webkit-line-clamp:2;
  -webkit-box-orient:vertical
}

.tip-list{
  display: flex;
  flex-flow: row wrap;
  overflow: hidden;
  height: 38px;
}

.tip-tags {
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

.tip-profile {
  font-size: 16px;
  display: flex;
  align-items: center;
  margin-top:10px;
}

.tip-like {
  margin-right: 10px;
}
</style>