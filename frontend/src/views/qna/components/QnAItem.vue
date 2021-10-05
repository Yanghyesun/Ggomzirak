<template>
    <div class="qna-item" :class="{border : clear}">
        <img src="@/assets/ic_Q.png" :width="48"/>
        <p class="qna-title">{{qna.title}}</p>
        <p class="qna-contents">{{qna.contents}}</p>
        <div class="qna-comments">
            <p v-if="clear" class="completed">답변 완료</p>
            <div style="flex: 1"></div>
            <div style="display: flex">
                <img src="@/assets/ic_fill_comments.png" :width="21" :height="18" style="margin-right: 8px"/>
                <p>{{qna.comments}}</p>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'QnAItem',
    props: {
        qna: Object
    },
    data() {
        return {
            clear: this.qna.comments > 0 ? true : false
        }
    }
}
</script>
<style scoped>
    .qna-item {
        width: 80%;
        border-radius: 16px;
        background-color: white;
        box-shadow: 0 0 6px 6px #f5f5f5;
        padding: 28px;
    }
    .border {
        border: 1px solid #CFC098;
    }
    .qna-title {
        font-size: 20px;
        margin-top: 36px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .qna-contents {
        font-size: 14px;
        margin-top: 18px;
        color: #898989;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .qna-comments {
        display: flex;
        margin-top: 42px;
        align-items: center;
    }
    .completed {
        background-color: #CFC098;
        border-radius: 16px;
        color: white;
        font-size: 12px;
        padding: 6px 16px;
    }
</style>