<template>
    <div style="display: flex; align-items: center;">
        <span class="live-subtitle"><span class="required">*</span>썸네일</span>
        <div class="live-contents" style="display: flex;">
            <div class="thumbnail-file-box">
                <p v-if="!isFile" style="color: #C3C3C3; font-size: 16px;">파일을 첨부해주세요!</p>
                <TextBox :name="filename" @delete="onDelete" v-if="isFile"/>
            </div>
            <label class="white-beige-btn">
                첨부
                <input type="file" style="display: none" @change="onCreate"/>
            </label>
        </div>
    </div>
</template>
<script>
import TextBox from './TextBox.vue'
export default {
    name: 'ThumbnailBox',
    components: {
        TextBox
    },
    props: {
        required: Boolean
    },
    data() {
        return {
            isFile: false,
            filename: '',
        }
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
        }
    },
    setup(props, { emit }) {
        const fileupload = (img) => {
            emit('fileupload', img);
        }
        const fileDelete = () => {
            emit('fileDelete');
        }
        return { fileupload, fileDelete };
    }
}
</script>
<style scoped>
    .live-subtitle {
        width: 80px;
    }
    .live-contents {
        width: 100%;
        overflow: hidden;
    }
    .thumbnail-file-box {
        flex: 1;
        overflow: hidden;
        border: 1px solid #dedede;
        border-radius: 8px;
        display: flex;
        align-items: center;
        padding-left: 12px;
        padding-top: 8px;
    }
    .white-beige-btn {
        border: 1px solid #CFC098;
        color: #CFC098;
        display: flex;
        align-items: center;
        padding: 14px 28px;
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