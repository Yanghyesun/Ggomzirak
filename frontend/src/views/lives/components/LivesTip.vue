<template>
    <div class="lives-tip">
        <p class="tab-title">
            <span class="contents-title">전체 라이브</span>
        </p>
        <div class="lives-tip-list">
            <p v-if="livesTip.length === 0" style="text-align: center">진행중인 라이브가 없습니다 :)</p>
            <LivesTipItem v-for="(live, idx) in livesTip" :key="idx" :live="live" :type="'lives'" @click="goDetail(live.id, live.endtime)" style="cursor: pointer;"/>
        </div>
    </div>
</template>
<script>
import LivesTipItem from './LivesTipItem.vue'

export default {
    name: 'LivesTip',
    components: {
        LivesTipItem
    },
    props: {
        livesTip: []
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
    },
}
</script>
<style scoped>
    .lives-tip-list {
        margin-top: 47px;
        display: grid;
        grid-template-columns: repeat(4, calc(95% / 4));
        row-gap: 12px;
        column-gap: 12px;
    }
    .tab-title {
        font-size: 24px;
        margin-left: 0.8rem;
    }
</style>