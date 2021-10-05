<template>
  <div class="lives">
    <BestLives :bestLives="bestLives" />
    <LivesTip :livesTip="livesTip" />
    <img src="@/assets/ic_live_btn.png" class="live-create-btn" @click="showCreateDialog"/>
    <LivesCreateDialog v-if="showDialog" @closeDialog="onCloseDialog"/>
  </div>
</template>

<script>
import LivesTip from './components/LivesTip.vue'
import BestLives from './components/BestLives.vue'
import LivesCreateDialog from './components/LivesCreateDialog.vue'
import $axios from "axios";
export default {
  name: 'Lives',
  components: {
    LivesTip,
    BestLives,
    LivesCreateDialog,
  },
  data() {
    return {
      showDialog: false,
      bestLives: [],
      livesTip: [],
    }
  },
  created() {
      //통신
      $axios.get('/main/live')
      .then((response) => {
        this.bestLives = response.data;
      })
      .catch(() => {
        console.log("오류가 발생했습니다.");
      });
      $axios.get('/live')
        .then((response) => {
            this.livesTip = response.data;
        }).catch(() => {
            console.log("라이브 전체 통신 실패");
        });
  },
  methods: {
    showCreateDialog() {
      this.showDialog = true;
    },
    onCloseDialog() {
      this.showDialog = false;
    }
  }
}
</script>

<style scoped>
  .best-lives {
        position: relative;
        height: 550px;
  }
  .lives-tip {
    width: 1250px;
    margin: 50px auto;
  }
  .live-create-btn {
    width: 80px;
    height: 80px;
    position: fixed;
    bottom: 42px;
    right: 42px;
  }
  .live-create-btn:hover {
    cursor: pointer;
  }
</style>