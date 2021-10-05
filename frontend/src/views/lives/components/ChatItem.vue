<template>
    <div class="chat-item">
        <div>
            <img :src="chat.userImage" class="user-image">
        </div>
        <div class="user-content">
            <p class="username">{{chat.username}}</p>
            <p class="contents">{{chat.msg}}</p>
        </div>
    </div>
</template>
<script>
export default {
    name: 'ChatItem',
    props: {
        chat: Object
    }
}
</script>
<style scoped>
    .chat-item {
        display: flex;
        margin: 16px;
    }
    .user-content {
        flex: 1;
        margin-top: 4px;
    }
    .user-image {
        width: 30px;
        height: 30px;
        border-radius: 50%;
        margin-right: 8px;
    }
    .username {
        color: #898989;
        font-size: 14px;
        margin-bottom: 4px;
    }
    .contents {
        background-color: white;
        border-radius: 2px 8px 8px 25px;
        color: #202020;
        font-size: 14px;
        padding:12px;
    }
</style>