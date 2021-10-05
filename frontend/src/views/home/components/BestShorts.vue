<template>
    <div class="best-shorts">
        <p style="margin-left: 0.8rem">
            <span class="contents-title">추천 꿀팁 Shorts</span>
        </p>
        <div class="shorts-list">
          <LivesTipItem v-for="(live, idx) in bestShorts" :key="idx" :live="live" :type="type" @click="goDetail(live.id,live.user.id)"/>
        </div>
      </div>
</template>
<script>
import LivesTipItem from '../../lives/components/LivesTipItem.vue'
export default {
    name: 'BestShorts',
    components: {
      LivesTipItem
    },
    props: {
      bestShorts: [],
      type: String
    },
    methods: {
        goDetail(id, userid) {
            this.$router.push(`/shorts/${id}?userId=${userid}`);
            this.$store.commit("setMenuActiveMenuName", "shorts");
        }
    }
}
</script>
<style scoped>
    .shorts-list {
      margin-top: 42px;
      display: grid;
      grid-template-columns: repeat(4, calc(95% / 4));
      row-gap: 12px;
      column-gap: 12px;
    }
    .lives-tip-item {
      cursor: pointer;
    }
</style>