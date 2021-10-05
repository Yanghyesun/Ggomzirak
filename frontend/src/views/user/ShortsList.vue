<template>
  <div class="ShortsList">
    <!-- <div class="shorts-list">
            <ShortsItem v-for="(shorts, idx) in shortsList" :key="idx" :shorts="shorts"/> -->
    <div
      v-masonry
      class="shorts-list"
      origin-top="true"
      horizontal-order="true"
      gutter="30"
      fit-width="true"
    >
      <ShortsItem v-masonry-tile v-for="(shorts, idx) in shortsList" :key="idx" :shorts="shorts" />
    </div>
  </div>
</template>
<script>
import ShortsItem from './ShortsItem.vue';
export default {
  name: 'ShortsList',
  components: {
    ShortsItem,
  },
  props: {
    shortsList: Object,
  },
};
</script>
<style scoped>
.shorts-list {
  margin-top: 47px;
  /* display: grid; */

  /* grid-template-columns: repeat(4, 1fr);
    row-gap: 24px;
    column-gap: 24px; */
  /* overflow: visible; */
}
</style>
