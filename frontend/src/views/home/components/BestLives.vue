<template>
    <div class="best-lives">
        <p style="margin-left: 0.8rem">
            <span class="contents-title">인기 꿀팁 라이브</span>
        </p>
        <div class="lives-list">
          <LivesTipItem v-for="(live, idx) in bestLives" :key="idx" :live="live" :type="'lives'" @click="goDetail(live.id, live.endtime)" style="cursor: pointer"/>
        </div>
    </div>
</template>
<script>
import LivesTipItem from '../../lives/components/LivesTipItem.vue'
export default {
    name: 'BestLives',
    components: {
      LivesTipItem
    },
    props: {
      bestLives: [],
      type: String
    },
    methods: {
        goDetail(id, endtime) {
            if(endtime === null) {
                this.$router.push(`/lives/${id}`);
            } else {
                this.$router.push(`/lives/save/${id}`);
            }
            this.$store.commit("setMenuActiveMenuName", "lives");
        }
    },
}
</script>
<style scoped>
    .lives-list {
      margin-top: 42px;
      display: grid;
      grid-template-columns: repeat(4, calc(95% / 4));
      row-gap: 12px;
      column-gap: 12px;
    }
</style>