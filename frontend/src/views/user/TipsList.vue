/* eslint-disable */

<template>
    <div class="TipsList">
        <div v-masonry class="tips-list" transition-duration="0.01s" origin-top="true" horizontalOrder: true
 gutter="28">
            <TipItem v-masonry-tile v-for="(tip, idx) in tipsList" :key="idx" :tip="tip"/>
        </div>
    </div>
</template>
<script>
import TipItem from './TipItem.vue'

export default {
    name: 'TipsList',
    components: {
        TipItem,
    },
    props: {
        tipsList: Object,
    }
}
</script>
<style scoped>
    .tips-list {
    margin-top: 47px;
    overflow: visible;
    /* column-gap: 24px; */
    }
</style>
