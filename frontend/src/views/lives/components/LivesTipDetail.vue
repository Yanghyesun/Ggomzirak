<template>
    <div class="lives-tip-detail">
        <p class="tab-title" style="font-size: 32px;">
            <span class="contents-title">{{live.title}}</span>
        </p>
        <div class="contents">
            <div class="live-video">
                <div v-if="showThumbnail" class="thumbnail">
                    <img src="@/assets/ic_play.png" @click="join" class="play-btn"/>
                </div>
                <user-video
                    :stream-manager="subscribe"
                />
                <p style="font-size: 18px; margin-top: 24px;">{{live.contents}}</p>
                <div class="live-info">
                    <div class="user-info">
                        <img :src="live.user.image" class="user-profile"/>
                        <p style="font-size: 16px;">{{live.user.name}}</p>
                    </div>
                    <div style="flex: 1"></div>
                    <div style="display: flex; font-size: 16px; ">
                        <div style="text-align: center; margin-right: 24px;">
                            <p>시청자 수</p>
                            <p>{{views}}</p>
                        </div>
                        <div style="text-align: center;">
                            <p>라이브 방송 시작일</p>
                            <p>{{live.starttime}}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="live-chat">
                <p class="chat-title">채팅</p>
                <div class="chat-list" ref="chatList">
                    <ChatItem v-for="(chat, idx) in chats" :key="idx" :chat="chat" />
                </div>
                <div class="chat-send">
                    <input type="text" class="ip-chat" placeholder="채팅을 시작하세요!" v-model="chatMsg"  v-on:keypress.enter="send"/>
                    <img src="@/assets/ic_send.png" class="ic-send"/>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import $axios from "axios";
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './UserVideo';
import ChatItem from './ChatItem.vue';

axios.defaults.headers.post["Content-Type"] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://i5a406.p.ssafy.io:8443';
const OPENVIDU_SERVER_SECRET = 'ggomzirak';

export default {
    name: 'LivesTipDetail',
    data() {
        return {
            OV: undefined,
            session: undefined,
            subscribe: undefined,
            showThumbnail: true,
            chats: [],
            live: Object,
            chatMsg: '',
            views: 0,
            sessionId: this.$route.params.userid,
        }
    },
    components: {
        UserVideo,
        ChatItem
    },
    created() {
        $axios.get('/live/' + this.sessionId)
        .then((response) => {
            this.live = response.data;
        }).catch(() => {
            console.log("라이브 상세 통신 실패");
        })
    },
    mounted() {
        this.join();
    },
    beforeUnmount() {
        this.leaveSession();
    },
    methods: {
        chatScroll() {
            this.$refs.chatList.scrollTop = this.$refs.chatList.scrollHeight;
        },
        getViews() {
            this.viewsInterval = setInterval(() => {
                axios
                .get(
                    `${OPENVIDU_SERVER_URL}/api/sessions/${this.sessionId}/connection`,
                    {
                        auth: {
                            username: "OPENVIDUAPP",
                            password: OPENVIDU_SERVER_SECRET,
                        },
                    }
                ).then((response) => {
                    this.views = response.data.numberOfElements - 1;
                    }).catch(() => {
                    console.log("시청자수 통신 실패");
                });
            }, 5000);
        },
        join() {
            this.OV = new OpenVidu();
            this.session = this.OV.initSession();

            this.session.on("streamCreated", ({ stream }) => {
                this.showThumbnail = false;
                const subscribe = this.session.subscribe(stream);
                this.subscribe = subscribe;
            });

            this.session.on("streamDestroyed", () => {
                this.showThumbnail = true;
                this.$router.push('/lives');
            });

            this.session.on("signal", event => {
                this.chats.push(JSON.parse(event.data));
                this.chatScroll();
            })

            this.getViews();

            this.createToken(this.sessionId).then((token) => {
                this.session
                .connect(token, { clientData: 'hyesun' })
                .then(() => {
                    
                })
                .catch((error) => {
                    console.log(
                        error.code,
                        error.message
                    );
                });
            });
        },
        leaveSession() {
            if (this.session) this.session.disconnect();

            this.session = undefined;
            this.subscriber = undefined;
            this.OV = undefined;

            this.showThumbnail = true;
            window.removeEventListener('beforeunload', this.leaveSession);
        },
        reset() {
            this.leaveSession();
            this.join();
        },
        createToken(sessionId) {
            return new Promise((resolve, reject) => {
                axios
                .post(
                    `${OPENVIDU_SERVER_URL}/api/tokens`,
                    JSON.stringify({
                        session: sessionId,
                    }),
                    {
                        auth: {
                            username: 'OPENVIDUAPP',
                            password: OPENVIDU_SERVER_SECRET,
                        },
                    }
                )
                .then((response) => response.data)
                .then((data) => resolve(data.token))
                .catch((error) => reject(error.response));
            });
        },
        send() {
            const userId = localStorage.getItem('userId');
            const userImage = localStorage.getItem('userImage');
            if(userId != null) {
                if(this.chatMsg != '') {
                    axios
                    .post(
                        `${OPENVIDU_SERVER_URL}/api/signal`,
                        JSON.stringify({
                            session: this.sessionId,
                            data: JSON.stringify({
                                username: userId,
                                userImage: userImage,
                                msg: this.chatMsg
                            })
                        }),
                        {
                            auth: {
                                username: 'OPENVIDUAPP',
                                password: OPENVIDU_SERVER_SECRET,
                            },
                        }
                    )
                    .then(() => {
                        console.log('보내기 성공');
                        this.chatMsg = '';
                    })
                    .catch(() => {
                        console.log('보내기 실패');
                    });
                }
            } else {
                alert("로그인 먼저 해주세요!");
            }
        }
    },
}
</script>
<style scoped>
    .lives-tip-detail {
        margin: 38px 65px;
    }
    .contents {
        height: 100%;
        display: flex;
        margin-top: 24px;
    }
    .live-video {
        flex: 2;
        margin-right: 24px;
        border-radius: 8px;
    }
    .live-view {
        width: 100%;
        background-color: gray;
        border-radius: 8px;
    }
    .live-chat {
        flex: 0.5;
        height: 790px;
        background-color: #F4F4F4;
        border-radius: 8px;
    }
    .live-info {
        display: flex;
        align-items: center;
    }
    .user-profile {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 8px;
    }
    .user-info {
        display: flex;
        align-items: center;
        margin-top: 20px;
    }
    .chat-title {
        font-size: 18px;
        margin-left: 8px;
        margin-top: 8px;
    }
    .thumbnail {
        height: 700px;
        position: relative;
        background-color: rgba(0,0,0,0.6);
        border-radius: 16px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .thumbnail:after {
        content : "";
        display: block;
        position: absolute;
        border-radius: 16px;
        top: 0;
        left: 0;
        background-image: url('https://m.young.hyundai.com:444/upload/CMS_NEWS_IMAGE/2017/12/01/CMS_NEWS_IMAGE_POd5lTOs2I0ydzAL9c5w.jpg');
        background-size: cover;
        width: 100%;
        height: 100%;
        z-index: -1;
    }
    .play-btn {
        
    }
    .play-btn:hover {
        cursor: pointer;
    }
    .chat-send {
        display: flex;
        background-color: white;
        border-radius: 8px;
        margin: 8px;
    }
    .ip-chat {
        flex: 1;
        border: 0px;
        background-color: transparent;
        font-size: 14px;
        padding-left: 12px;
        padding-right: 6px;
    }
    .chat-list {
        height: 690px;
        overflow: auto;
        margin-top: 12px;
        margin-bottom: 12px;
    }
    .ic-send {
        width: 32px;
        padding: 3px;
    }
</style>