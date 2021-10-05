<template>
    <div class="video-item">
        <img :src="video.url" class="video-img">
        <p class="video-title">{{video.title}}</p>
        <p class="video-hits" v-if="type === 'shorts'">조회수 <span>{{video.hits}}</span> | 댓글수 <span>{{video.comments}}</span></p>
        <p class="video-hits" v-if="type === 'lives'">시청자수 {{video.hits}}</p>
    </div>
</template>
<script>
export default {
    name: 'HomeVideoItem',
    props: {
        video: Object,
        type: String
    }
}
</script>
<style scoped>
    .video-item {
      font-size: 18px;
      flex: 1;
      margin-right: 24px;
    }
    .video-img {
        width: 100%;
    }
    .video-title {
        margin-top: 10px;
    }
    .video-hits {
        margin-top: 8px;
    }
</style>