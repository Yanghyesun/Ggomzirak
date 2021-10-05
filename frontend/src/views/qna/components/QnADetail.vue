<template>
    <div class="qna-detail">
        <div class="question">
            <div>
                <img src="@/assets/ic_question.png" :width="94" :height="72"/>
            </div>
            <div class="info">
                <p class="question-title">{{qna.title}}</p>
                <p class="question-contents">{{qna.contents}}</p>
                <div class="img-list">
                    <img class="detail-img" v-for="(img,idx) in images" :key="idx" :src="img"/>
                </div>
                <div class="tag-list">
                    <p class="tag" v-for="(tag,idx) in tags" :key="idx" :value="tag">#{{tag}}</p>
                </div>
                
                <div class="user-info">
                    <img :src="qna.user.image" class="profile"/>
                    <p>{{qna.user.name}}</p>
                    <div style="flex: 1"></div>
                    <p style="color: #898989;">{{qna.time}}</p>
                </div>
            </div>
        </div>
        <div style="background-color: #f9f9f9;">
            <div class="answer">
                <div>
                    <img src="@/assets/ic_answer.png"  :width="93" :height="72"/>
                </div>
                <div class="info">
                    <div class="comments-list">
                        <p class="empty" v-if="comments.length === 0">댓글이 없네요 회원님의 답변을 기다리고 있습니다:) </p>
                        <CommentItem v-for="(comment, idx) in comments" :key="idx" :comment="comment"/>
                    </div>
                    <form>
                        <div style="width: 100%;">
                            <textarea rows="4" placeholder="답변을 달아주세요!" class="comment-input" v-model="commentsContent"></textarea>
                        </div>
                        <div style="display: flex;">
                            <div style="flex: 1"></div>
                            <div class="create-btn" @click="commentsCreate">등록</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import CommentItem from './CommentItem.vue';
import $axios from 'axios';
import check from '@/common/check.js';

export default {
    name: 'QnADetail',
    components: {
        CommentItem
    },
    data() {
        return {
            qna: Object,
            tags: [],
            images: [],
            comments: [],
            commentsContent: '',
            idx: -1
        }
    },
    created() {
        this.idx = this.$route.params.id;
        $axios.get('/qna/' + this.idx)
        .then((response) => {
            this.qna = response.data.qna;
            this.tags = response.data.tags;
            this.images = response.data.images;
        })
        .catch((error) => {
            console.log(error);
        });

        this.getCommentList();
    },
    methods: {
        commentsCreate() {
            const userId = localStorage.getItem('id');
            const data = {
                'contents' : this.commentsContent,
                'postId' : this.idx,
                'typeId' : 3,
                'userId' : userId
            };

            let myheaders = {};
            if (check == 0) {
            myheaders = {
                'jwt-auth-access-token': localStorage.getItem('accessToken'),
            };
            } else {
            myheaders = {
                'jwt-auth-refresh-token': localStorage.getItem('refreshToken'),
            };
            }

            $axios.post('/qna/comments/register', data,{ headers:myheaders,})
            .then(() => {
                alert("답변 감사합니다 :)");
                this.commentsContent = '';
                this.getCommentList();
            })
            .catch(() => {
                alert("답변 등록에 오류가 발생했습니다.");
            });
        },
        getCommentList() {
            $axios.get('/qna/comments/' + this.idx + '?page=1')
            .then((response) => {
                this.comments = response.data;
            })
            .catch((error) => {
                console.log(error);
            });
        }
    }
}
</script>
<style scoped>
    .question {
        display: flex;
        width: 1250px;
        margin: 65px auto;
    }
    .info {
        flex: 1;
        margin-left: 36px;
        margin-top: 28px;
    }
    .question-title {
        font-size: 32px;
    }
    .question-contents {
        font-size: 20px;
        color: #898989;
        margin-top: 42px;
    }
    .img-list {
        display: flex;
        margin-top: 16px;
    }
    .detail-img {
        width: 200px;
        height: 200px;
        object-fit: cover;
        border-radius: 8px;
    }
    .tag-list {
        display: flex;
        margin-top: 36px;
    }
    .tag {
        border: 1px solid #CFC098;
        color: #CFC098;
        border-radius: 16px;
        padding: 8px 16px 5px 16px;
        font-size: 14px;
        margin-right: 6px;
    }
    .user-info {
        display: flex;
        margin-top: 24px;
        align-items: center;
    }
    .profile {
        width: 28px;
        height: 28px;
        border-radius: 50%;
        margin-right: 12px;
    }

    .answer {
        display: flex;
        width: 1250px;
        margin: 0 auto;
        margin-top: 65px;
        padding: 64px 0px;
    }
    .comment-input {
        width: calc(100% - 24px);
        font-size: 16px;
        padding: 12px;
        resize: none;
        border: 1px solid #DEDEDE;
        border-radius: 8px;
    }
    .create-btn {
        background-color: #CFC098;
        padding: 10px 15px;
        color: white;
        border-radius: 8px;
    }
    .create-btn:hover {
        cursor: pointer;
    }
    .empty {
        color: #898989;
        margin-bottom: 16px;
    }
</style>