<template>
    <div class="best-tips">
        <p style="margin-left: 0.8rem">
            <span class="contents-title">이 주의 베스트 꿀팁</span>
        </p>
        <div class="tips-list">
            <BestTipsItem v-for="(tip, idx) in bestTips" :key="idx" :tip="tip" @click="goDetail(tip.id,tip.user.id)"/>
        </div>
    </div>
</template>
<script>
import BestTipsItem from './BestTipsItem.vue'
export default {
    name: 'BestTips',
    components: {
        BestTipsItem
    },
    props: {
        bestTips: []
    },
    methods: {
        goDetail(id, userid) {
            this.$router.push(`/tips/${id}?userId=${userid}`);
            this.$store.commit("setMenuActiveMenuName", "tips");
        }
    }
}
</script>
<style scoped>
    .tips-list {
        display: flex;
        flex-wrap: wrap;
        margin-top: 42px;
    }
    .tips-item:hover {
        cursor: pointer;
    }
</style>