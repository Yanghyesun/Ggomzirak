/* eslint-disable */

<template>
    <div class="TipsList">
        <div v-masonry class="tips-list" origin-top="true" horizontal-order="true" gutter="30" fit-width="true">
            <p v-if="tipsList.length===0">검색 결과가 없습니다 :)</p>
            <TipItem v-masonry-tile v-for="(tip, idx) in tipsList" :key="idx" :tip="tip" @click="goDetail(tip.id)"/>
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
    },
    methods: {
        goDetail(idx) {
            window.location.href = '/tips/' + idx;
        }
    }
}
</script>
<style scoped>
    .tips-list {
    margin-top: 47px;
    }
</style>
