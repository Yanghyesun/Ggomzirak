<template>
    <div class="tips-item">
        <img :src="tip.thumbnail" class="thumbnail"/>
        <div class="tips-item-contents">
            <p class="title">{{tip.title}}</p>
            <div class="info">
                <div class="tips-like">
                    <img src="@/assets/ic_fill_like.png" :width="16" style="margin-right: 6px">
                    <span>
                        {{tip.likes}}
                    </span>
                </div>
                <div class="tips-com">
                    <img src="@/assets/ic_fill_comments.png" :width="16" style="margin-right: 6px">
                    <span>
                        {{tip.comments}}
                    </span>
                </div>
            </div>
        </div>
        
    </div>
</template>
<script>
export default {
    name: 'BestTipsItem',
    props: {
        tip: Object
    }
}
</script>
<style scoped>
    .tips-item {
        height: 210px;
        position: relative;
        flex: 1 1 40%;
        margin-right: 12px;
        margin-bottom: 12px;
        overflow: hidden;
    }
    .thumbnail {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        opacity: 0.2;
        object-fit: cover;
        border-radius: 8px;
    }
    .tips-item-contents {
        font-size: 20px;
        position: absolute;
        top: 0;
        left: 0;
        width: calc(100% - 16px);
        height: 100%;
        margin: 0 8px;
        overflow: hidden;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .title {
        width: 100%;
        text-align: center;
        font-size: 18px;
        margin-bottom: 8px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .info {
        display: flex;
        align-items: center;
    }
    .tips-like {
        font-size: 16px;
        margin-right: 8px;
    }
    .tips-com {
        font-size: 16px;
    }
</style>