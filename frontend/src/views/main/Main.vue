<template>
    <div class="main">
        <MainHeader/>
        <router-view />
    </div>
</template>
<script>
import MainHeader from './components/MainHeader.vue'

export default {
    name: 'Main',
    components: {
        MainHeader
    }
}
</script>
<style scoped>
</style>