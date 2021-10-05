<template>
    <div class="save-live">
        <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; margin-top: 16px;">
            <div class="info">
                <div style="flex: 3; margin-right: 32px;">
                    <p class="tab-title">
                        <span class="contents-title">{{live.title}}</span>
                    </p>
                    <video controls autoplay class="video">
                        <source :src="live.video" type="video/mp4" />
                    </video>
                    <p style="font-size: 20px; margin-top: 16px;">{{live.contents}}</p>
                    <div class="live-info">
                        <img :src="live.user.image" class="user-profile"/>
                        <p style="font-size: 14px;" class="username">{{live.user.name}}</p>
                    </div>
                </div>
                <div style="flex: 0.5;">
                    <p style="font-size: 18px; padding: 22px 0;">#인기 라이브</p>
                    <LivesTipItem v-for="(live,idx) in bestLives" :key="idx" :type="'lives'" :live="live"  @click="goDetail(live.id, live.endtime)" style="cursor: pointer;"/>
                </div>
            </div>          
        </div>
    </div>
</template>
<script>
import $axios from 'axios';
import LivesTipItem from './LivesTipItem.vue'
export default {
    name: 'SaveLive',
    components: {
        LivesTipItem,
    },
    data() {
        return {
            live: Object,
            id: this.$route.params.id,
            bestLives: []
        }
    },
    created() {
        $axios.get('/live/' + this.id)
        .then((response) => {
            this.live = response.data;
        }).catch(() => {
            console.log("라이브 상세 통신 실패");
        });

        $axios.get('/main/live')
        .then((response) => {
            this.bestLives = response.data;
        })
        .catch(() => {
            console.log("오류가 발생했습니다.");
        });
    },
    methods: {
        goDetail(id, endtime) {
            if(endtime === null) {
                window.location.href = `/lives/${id}`;
            } else {
                window.location.href = `/lives/save/${id}`;
            }
        }
    }
}
</script>
<style scoped>
    .save-live {
        width: 100%;
    }
    .tab-title {
        font-size: 36px;
    }
    .info {
        margin-top: 32px;
        display: flex;
    }
    .video {
        margin-top: 16px;
        width: 100%;
        max-width: 1100px;
    }
    .live-info {
        display: flex;
        margin-top: 12px;
        align-items: center;
    }
    .user-profile {
        width: 38px;
        height: 38px;
        border-radius: 50%;
        margin-right: 8px;
    }
</style>