/* eslint-disable */
<template>
    <div class="nav">
        <div class="menu-item">
            <p style="margin-left: 0.8rem">
                <span class="contents-title">
                <router-link to="/tips" class="deco-none">꿀팁</router-link>
                </span>
            </p>
        </div>
        <div class="menu-item">
            <p style="margin-left: 0.8rem">
                <span class="contents-title">
                <router-link to="/shorts" class="deco-none">Shorts</router-link>
                </span>
            </p>
        </div>
    </div>
    <div class="searchbar">
        <input type="text" placeholder="궁금한 꿀팁을 캐보세요.">
    </div>
</template>
<script>
export default {
    name: 'MainHeader',
}
</script>
<style scoped>
    /* .header {
        background-color: white;
        box-shadow: 0 0 3px 3px #eeeeee;
    } */
    .nav {
        display: flex;
        height: 45px;
        align-items: center;
        font-size: 15px;
    }
    .searchbar {
        display: flex;
        
    }
    .menu-item {
        margin: 0 5px;
    }
</style>