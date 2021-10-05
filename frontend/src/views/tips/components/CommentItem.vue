<template>
    <div class="comment-item">
        <div class="user-info">
            <img :src="comment.user.image" class="profile"/>
            <p>{{comment.user.name}}</p>
            <div style="flex: 1"></div>
            <p class="time">{{comment.time}}</p>
        </div>
        <p style="padding: 0px 0px 18px 0px; font-size: 16px;">{{comment.contents}}</p>
    </div>
</template>
<script>
export default {
    name: 'CommentItem',
    props: {
        comment: Object
    }
}
</script>
<style scoped>
    .comment-item {
        border-bottom: 1px solid #DEDEDE;
        background-color: white;
        margin-bottom: 36px;
    }
    .user-info {
        display: flex;
        align-items: center;
        font-size: 16px;
        padding: 20px 0px;
    }
    .profile {
        width: 36px;
        height: 36px;
        border-radius: 50%;
        margin-right: 12px;
    }
    .time{
        color: #898989;
    }
</style>