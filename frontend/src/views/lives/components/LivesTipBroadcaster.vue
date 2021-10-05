<template>
  <div class="live-broadcaster">
    <div class="live-video">
      <user-video :stream-manager="mainStreamManager" />
      <div style="margin-left: 16px; margin-top: 16px">
        <p class="title tab-title">
          <span class="contents-title">{{ live.title }}</span>
        </p>
        <p style="font-size: 18px; margin-top: 16px">{{ live.contents }}</p>
        <div class="info">
          <p style="font-size: 18px">시작 후 {{ time }} | 시청자 수 {{ views }}</p>
          <div style="flex: 1"></div>
          <p :class="isMute ? 'live-exit' : 'mute'" @click="toggleMute" style="margin-right: 8px">
            음소거
          </p>
          <p class="live-exit" @click="leaveSession">종료</p>
        </div>
      </div>
    </div>
    <div class="live-chat">
      <div class="chat-title">
        <p style="margin-left: 16px">채팅</p>
      </div>
      <div class="chat-list" ref="chatList">
        <ChatItem v-for="(chat, idx) in chats" :key="idx" :chat="chat" />
      </div>
    </div>
    <div v-if="isSave" class="save-live">
      <div class="save-live-box">
        <div>
            <p v-if="isLoading">저장 중.................</p>
            <p style="font-size: 18px;">라이브를 저장하시겠습니까?</p>
            <div style="display: flex; justify-content: center; margin-top: 16px;">
              <p class="save-btn" @click="saveRecording()">저장</p>
              <p class="cancel-btn" @click="cancelRecording()">취소</p>
            </div>
        </div>
        
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import $axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './UserVideo';
import ChatItem from './ChatItem.vue';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://i5a406.p.ssafy.io:8443/openvidu';
const OPENVIDU_SERVER_SECRET = 'ggomzirak';

export default {
  name: 'LivesTipBroadcaster',
  components: {
    UserVideo,
    ChatItem,
  },
  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: this.$route.params.userid,
      live: Object,
      chats: [],
      time: '00:00:00',
      hour: 0,
      min: 0,
      sec: 0,
      cnt: 0,
      views: 0,
      recordingId: '',
      isMute: false,
      interval: undefined,
      viewsInterval: undefined,
      isSave: false,
      isLoading: false
    };
  },
  mounted() {
    $axios
      .get('/live/' + this.mySessionId)
      .then((response) => {
        this.live = response.data;
      })
      .catch(() => {
        console.log('라이브 상세 통신 실패');
      });
    this.join();
  },
  beforeUnmount() {
    this.leaveSession();
  },
  methods: {
    chatScroll() {
      this.$refs.chatList.scrollTop = this.$refs.chatList.scrollHeight;
    },
    startInterval() {
      this.timeInterval = setInterval(() => {
        this.cnt++;

        this.min = Math.floor(this.cnt / 60);
        this.hour = Math.floor(this.min / 60);
        this.sec = this.cnt % 60;
        this.min = this.min % 60;

        let h = this.hour;
        let m = this.min;
        let s = this.sec;

        if (h < 10) {
          h = '0' + this.hour;
        }
        if (m < 10) {
          m = '0' + this.min;
        }
        if (s < 10) {
          s = '0' + this.sec;
        }

        this.time = h + ':' + m + ':' + s;
      }, 1000);
    },
    getViews() {
      this.viewsInterval = setInterval(() => {
        axios
          .get(`${OPENVIDU_SERVER_URL}/api/sessions/${this.mySessionId}/connection`, {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET,
            },
          })
          .then((response) => {
            this.views = response.data.numberOfElements - 1;
            const data = {
              liveid: this.mySessionId,
              viewers: this.views,
            };
            $axios
              .put('/live/viewers', data)
              .then(() => {
                
              })
              .catch(() => {
                console.log('시청자수 저장 실패');
              });
          })
          .catch(() => {
            console.log('시청자수 통신 실패');
          });
      }, 5000);
    },
    toggleMute() {
      this.isMute = !this.isMute;
      if (this.isMute) {
        this.publisher.publishAudio(false);
      } else {
        this.publisher.publishAudio(true);
      }
    },
    join() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on('streamDestroyed', () => {});

      this.session.on('signal', (event) => {
        this.chats.push(JSON.parse(event.data));
        this.chatScroll();
      });

      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.mySessionId })
          .then(() => {
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: '1280x720', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;
            this.session.publish(this.publisher);
            this.startRecording(this.mySessionId);

            //시작시간 start
            this.startInterval();
            //시청자 수 count
            this.getViews();
          })
          .catch((error) => {
            console.log('There was an error connecting to the session:', error.code, error.message);
          });
      });
    },
    leaveSession() {
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      $axios
        .put('/live/' + this.mySessionId)
        .then(() => {
          clearInterval(this.timeInterval);
          clearInterval(this.viewsInterval);
          this.isSave = true;
        })
        .catch(() => {
          console.log('라이브 상세 통신 실패');
        });

      window.removeEventListener('beforeunload', this.leaveSession);
    },

    saveRecording() {
      this.isLoading = true;
      setTimeout(() => {
          axios
          .get(`${OPENVIDU_SERVER_URL}/api/recordings/${this.recordingId}`,
          {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET,
            },
          }).then((response) => {
            
            const formData = new FormData();
            formData.append('liveid', this.mySessionId);
            formData.append('record', response.data.url);

            axios.post('https://ggomzirak.site/api/v1/live/recording', formData, {headers: {"Content-Type" : "multipart/form-data"}})
            .then(() => {
              this.isLoading = false;
              console.log("녹음 정보 저장 성공");
              this.$router.push('/lives');
            })
            .catch(() => {
              console.log("녹음 정보 저장 실패");
            });
          }).catch(() => {
            console.log("녹음 정보 조회 실패");
          });
      }, 4000);
    },

    cancelRecording() {
        this.$router.push('/lives');
    },

    startRecording(sessionId) {
      axios
        .post(
          `${OPENVIDU_SERVER_URL}/api/recordings/start`,
          JSON.stringify({
            session: sessionId,
            name: sessionId,
            hasAudio: true,
            hasVideo: true,
            outputMode: 'COMPOSED',
            recordingLayout: 'BEST_FIT',
            resolution: '1280x720',
            frameRate: 60,
            shmSize: 536870912,
            ignoreFailedStreams: false,
            mediaNode: {
              id: 'media_i-0c58bcdd26l11d0sd',
            },
          }),
          {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET,
            },
          }
        )
        .then((response) => {
          this.recordingId = response.data.id;
        })
        .catch(() => {
          console.log('녹음 시작 실패');
        });
    },

    stopRecording(recordingId) {
      axios
        .post(`${OPENVIDU_SERVER_URL}/api/recordings/stop/${recordingId}`, {
          auth: {
            username: 'OPENVIDUAPP',
            password: OPENVIDU_SERVER_SECRET,
          },
        })
        .then(() => {
          console.log('녹음 중지 성공!');
        })
        .catch(() => {
          console.log('녹음 중지 실패');
        });
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
    },

    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
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
  },
};
</script>
<style scoped>
.live-broadcaster {
  background-color: white;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  min-height: 900px;
  display: flex;
  z-index: 999;
}
.live-video {
  width: 82%;
  height: 100%;
}
.title {
  font-size: 24px;
  margin-left: 0.8rem;
}
.info {
  display: flex;
  margin-top: 16px;
  margin-right: 16px;
  align-items: center;
}
.live-exit {
  background-color: #cfc098;
  padding: 16px 80px;
  color: white;
  border-radius: 8px;
}
.mute {
  border: 1px solid #dedede;
  color: #dedede;
  padding: 16px 80px;
  border-radius: 8px;
}
.live-chat {
  width: 18%;
  height: 100%;
  background-color: #f4f4f4;
}
.chat-title {
  font-size: 18px;
  height: 40px;
  display: flex;
  align-items: center;
  background-color: #dedede;
}
.chat-list {
  height: calc(100% - 40px);
  overflow: auto;
  /* -ms-overflow-style: none; */
}
/* .chat-list::-webkit-scrollbar{ 
        display: none;
    } */

  .save-live {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #000000aa;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .save-live-box {
    width: 300px;
    height: 200px;
    background-color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 8px;
  }
  .save-btn {
    background-color: #cfc098;
    color: white;
    padding: 8px 16px;
    border-radius: 8px;
    margin-right: 4px;
  }
  .cancel-btn {
    border: 1px solid #dedede;
    color: #dedede;
    padding: 8px 16px;
    border-radius: 8px;
  }
</style>
