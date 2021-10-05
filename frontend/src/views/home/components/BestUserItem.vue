<template>
    <div class="best-user-item" :style="marginStyle">
        <div style="position: relative;">
            <div class="img-bg" :style="imgStyle">
                <img :src="user.image" :width="user.size" :height="user.size" class="user-img"/>
            </div>
            <p class="level">{{user.ranking}}</p>
        </div>
        <p class="user-name">{{user.username}}</p>
        <div class="count-info">
            <img src="@/assets/ic_fill_like.png" class="ic-img"/>
            <p class="count">{{user.likes}} like</p>
        </div>
        <div class="count-info">
            <img src="@/assets/ic_fill_hit.png" class="ic-img"/>
            <p class="count">{{user.views}} view</p>
        </div>
        <div class="count-info">
            <img src="@/assets/ic_write.png" class="ic-img"/>
            <p class="count">{{user.posts}} post</p>
        </div>
        <p class="get-point">+ {{user.point}}P</p>
    </div>
</template>
<script>
export default {
    name: 'BestUserItem',
    props: {
        user: Object
    },
    data() {
        return {
            imgStyle: 'width: ' + (this.user.size + 30) + 'px; height: ' + (this.user.size + 30) + 'px;',
            marginStyle: 'margin-top: ' + this.user.top + 'px;'
        }
    }
}
</script>
<style scoped>
    .best-user-item {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .best-user-img {
        text-align: center;
    }
    .img-bg {
        background-color: #f0ebde;
        border-radius: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .user-img {
        border-radius: 50%;
    }
    .level {
        position: absolute;
        top: 14px;
        left: 5px;
        background-color: #b6a881;
        color: white;
        border-radius: 50%;
        text-align: center;
        padding: 8px 13px;
        font-size: 16px;
    }
    .user-name {
        font-size: 18px;
        margin: 18px 0;
    }
    .count-info {
        display: flex;
        font-size: 14px;
        width: 100px;
        align-items: center;
    }
    .ic-img {
        width: 18px;
        height: 18px;
        padding: 4px;
        margin-right: 12px;
    }
    .get-point {
        font-size: 12px;
        background-color: #E88989;
        border-radius: 16px;
        color: white;
        display: inline-block;
        padding: 6px 18px 4px 18px;
        margin-top: 24px;
    }
</style>