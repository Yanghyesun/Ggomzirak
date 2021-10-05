<template>
    <div class="best-user">
        <p style="margin-left: 0.8rem">
            <span class="contents-title">이 주의 베스트 유저</span>
        </p>
        <div class="best-user-list">
            <BestUserItem v-for="(user, idx) in bestUser" :key="idx" :user="user"/>
        </div>
    </div>
</template>
<script>
import BestUserItem from './BestUserItem.vue'
export default {
    name: 'BestUser',
    components: {
        BestUserItem
    },
    props: {
        bestUser: []
    }
}
</script>
<style scoped>
    .best-user {
        margin-left: 40px;
    }
    .best-user-list {
        display: flex;
        justify-content: space-around;
    }
</style>