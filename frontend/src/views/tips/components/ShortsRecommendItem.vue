<template>
  <div class="shortsrecommenditem" style="cursor:pointer;" @click="goDetail(shorts.id)">
    <div class="shortsitem-img">
      <img :src="shorts.thumbnail" style="width: 100%;" class="shorts-img">
    </div>
    
    <div class="container-text">
      <div class="shorts-title" :title="shorts.title">
        <p style="font-size: 14px"><b>{{shorts.title}}</b></p>
      </div>
        <p style="font-size: 12px; margin-top: 5px;">{{shorts.user.name}}</p>
        <img src="@/assets/ic_view.png" style="width: 12px; height: 12px; margin-top:5px; margin-right: 3px">
        <span style="font-size: 12px;">
            {{shorts.views}}
        </span>
        <img src="@/assets/ic_like.png" style="width: 12px; height: 12px; margin-right: 3px; margin-left: 7px;">
        <span style="font-size: 12px">
            {{shorts.likes}}
        </span>

    </div>
  </div>
</template>

<script>
// import {useRouter} from 'vue-router'

export default {
  name: 'ShortsItem',
  props: {
    shorts: Object,
    tagList: [],
  },
  //   setup(props) {
  //   const router = useRouter()

  //   const goDetail = ()  => {
  //       router.push({
  //           name: 'ShortsDetail',
  //           params: {
  //               id: props.shorts.id
  //           },
  //           query: { userId: props.shorts.user.id }
  //       })
  //   }

  //   return { goDetail }
  // }
  methods: {
      goDetail(shortsId) {
          window.location.href = '/shorts/' + shortsId + '?userId=' + localStorage.getItem('id');
      }
  },  
}
</script>

<style scoped>
/* .card {
  box-shadow: 0 8px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 100%;
  border-radius: 20px;
  flex-direction: column;
} */
/* 
.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
} */

/* .img {
  border-radius: 20px 20px 0 0;
  height: 200px;
  display: flex;
  flex-direction: column;

} */


.shortsrecommenditem {
  width: 300px;
  display: flex;
  border-radius: 20px;
    /* border: 1px solid #cfc098; */
    /* background-color: #dedede; */

}

  .shortsitem-img {
    width: 160px;
    height: 90px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    /* object-fit: contain;
    align-self: flex-start;
    border-radius: 50px; */
}

 .shorts-img {
   width: 160px;
   height: 90px;
   border-radius: 10px;

 }

  .container-text {
    margin-left: 10px;
    width:120px;
    margin-top: 10px;
    margin-right: 10px;

    /* flex: 1 1 auto; */
  }

  .shorts-title {
    width:120px;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;   
    display:-webkit-box;
  }

/* .container {
  padding: 2px 16px;
  height: 200px;
  display: flex;
}

.shorts-item-info {
  height: auto;
}

.shorts-like {
  display: inline-block;
  font-size: 16px;
  margin-right: 8px;
}
.shorts-hit {
  display: inline-block;
  font-size: 16px;
} */



</style>