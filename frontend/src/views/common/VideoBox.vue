<template>
  <div style="display: flex; align-items: center">
    <span class="live-subtitle"><span class="required">*</span>동영상</span>
    <div class="live-contents" style="display: flex">
      <div class="video-file-box">
        <p v-if="!isFile" style="color: #dedede; font-size: 16px">파일을 첨부해주세요!</p>
        <TextBox :name="filename" @delete="onDelete" v-if="isFile" />
      </div>
      <label class="white-beige-btn">
        첨부
        <input type="file" style="display: none" @change="onCreate" />
      </label>
    </div>
  </div>
</template>
<script>
import TextBox from './TextBox.vue';
export default {
  name: 'VideoBox',
  components: {
    TextBox,
  },
  props: {
    required: Boolean,
  },
  data() {
    return {
      isFile: false,
      filename: '',
    };
  },
  methods: {
    onDelete() {
      this.isFile = false;
      this.filename = false;
    },
    onCreate(event) {
      const img = event.target.files[0];
      this.isFile = true;
      this.fileupload(img);
      this.filename = img.name;
    },
  },
  setup(props, { emit }) {
    const fileupload = (vd) => {
      emit('videoupload', vd);
    };
    const fileDelete = () => {
      emit('videoDelete');
    };
    return { fileupload, fileDelete };
  },
};
</script>
<style scoped>
.live-subtitle {
  width: 80px;
}
.live-contents {
  width: 100%;
}
.video-file-box {
  flex: 1;
  overflow: hidden;
  border: 1px solid #dedede;
  border-radius: 8px;
  display: flex;
  align-items: center;
  padding-left: 8px;
}
.white-beige-btn {
  border: 1px solid #cfc098;
  color: #cfc098;
  padding: 14px 28px;
  display: flex;
  align-items: center;
  border-radius: 8px;
  margin-left: 8px;
}
.white-beige-btn:hover {
  cursor: pointer;
}
.required {
  color: #cfc098;
  font-size: 18px;
  margin-right: 8px;
}
</style>
