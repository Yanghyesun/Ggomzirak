<template>
    <div class="lives-tip-item">
        <div class="lives-thumbnail">
            <img :src="live.thumbnail" class="thumbnail" />
            <div class="thumbnail-header" v-if="type === 'lives' && live.endtime === null">
                <!-- live icon -->
                <div class="live-icon">
                    <div class="red-circle"></div>
                    <p>LIVE</p>
                </div>
                <!-- viewer count -->
                <div class="viewer-count">
                    <img src="@/assets/ic_viewer.png" :width="9" :height="9" style="margin-right: 4px;"/>
                    <p>{{live.viewers}}</p>
                </div>
            </div>
            <div v-if="type === 'lives' && live.type === 2" class="hunsu">
                <div style="flex: 1"></div>
                <p style="background-color: #cfc098; padding: 4px 8px; border-radius: 8px; font-size: 12px; color: white;">훈수</p>
            </div>
        </div>
        <div class="tip-info">
            <img :src="live.user.image" class="user-profile"/>
            <div style="width: 85%;">
                <p class="title">{{live.title}}</p>
                <p style="font-size: 14px;" class="username">{{live.user.name}}</p>
            </div>
        </div>
        <div v-if="type === 'shorts'" class="hits-info">
            <img src="@/assets/ic_fill_hit.png" :width="15" :height="14" style="margin-right: 4px;"/>
            <p style="margin-right: 8px;  font-size: 14px;">{{live.views}}</p>
            <img src="@/assets/ic_fill_like.png" :width="16" :height="14" style="padding: 1px 0; margin-right: 4px;"/>
            <p style="font-size: 14px;">{{live.likes}}</p>
        </div>
    </div>
</template>
<script>
export default {
    name: 'LivesTipItem',
    props: {
        live: Object,
        type: String,
    },
}
</script>
<style scoped>
    .lives-tip-item {
        margin-bottom: 8px;
    }
    .lives-thumbnail {
        height: 200px;
        position: relative;
    }
    .thumbnail {
        position: absolute;
        border-radius: 8px;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .thumbnail-header {
        position: absolute;
        top: 0;
        left: 0;
        width: calc(100% - 12px);
        display: flex;
        margin-top: 12px;
        margin-left: 12px;
    }
    .live-icon {
        background-color: white;
        border-radius: 5px;
        display: flex;
        font-size: 12px;
        padding: 2px 6px;
        margin-right: 8px;
        align-items: center;
    }
    .red-circle {
        width: 10px;
        height: 10px;
        margin-right: 4px;
        border-radius: 50%;
        background-color: #E83333;
    }
    .viewer-count {
        color: white;
        font-size: 12px;
        display: flex;
        background-color: #202020aa;
        border-radius: 5px;
        padding: 2px 6px;
        align-items: center;
    }
    .user-profile {
        width: 38px;
        height: 38px;
        border-radius: 50%;
        margin-right: 8px;
    }
    .tip-info {
        display: flex;
        margin-top: 12px;
    }
    .hits-info {
        display: flex;
        font-size: 18px;
        align-items: center;
        justify-content: flex-end;
        margin-top: -10px;
    }
    .title {
        font-size: 18px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .hunsu {
        display: flex;
        position: absolute;
        bottom: 5px;
        right: 5px;
    }
</style>