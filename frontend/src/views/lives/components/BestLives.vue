<template>
    <div class="best-lives">
        <span class="best-lives-bg"></span>
        <div class="best-lives-list">
            <p class="tab-title">
                <span class="contents-title">인기 라이브</span>
            </p>
            <div style="display: grid; grid-template-columns: repeat(4, calc(95% / 4)); column-gap: 12px;">
                <LivesTipItem v-for="(live,idx) in bestLives" :key="idx" :type="'lives'" :live="live"  @click="goDetail(live.id, live.endtime)" style="cursor: pointer;"/>
            </div>
        </div>
    </div>
</template>
<script>
import LivesTipItem from './LivesTipItem.vue'
export default {
    name: 'BestLives',
    components: {
        LivesTipItem,
    },
    props: {
        bestLives: []
    },
    data() {
        return {
        }
    },
    methods: {
        goDetail(id, endtime) {
            if(endtime === null) {
                this.$router.push(`/lives/${id}`);
            } else {
                this.$router.push(`/lives/save/${id}`);
            }
        }
    }
}
</script>
<style scoped>
    .best-lives-bg {
        position: absolute;
        width: 100%;
        height: 310px;
        top: 0px;
        left: 0px;
        background-color: #EEEEEE;
        z-index: -2;
    }
    .tab-title {
        font-size: 24px;
        padding-top: 62px;
        width: 1250px;
        margin: 0 auto;
        padding-bottom: 62px;
    }
    .best-lives-list {
        width: 1250px;
        margin: 0 auto;
    }
</style>