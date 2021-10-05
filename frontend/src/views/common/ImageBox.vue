<template>
    <div class="lb">
        <div class="live-subtitle">
            <span class="required" v-show="required">*</span>
            이미지
        </div>
        <div class="live-contents" style="display: flex;">
            <div class="thumbnail-file-box">
                <p v-if="filenames.length === 0" style="color: #C3C3C3; font-size: 16px;">파일을 첨부해주세요!</p>
                <TextBox v-for="(filename, idx) in filenames" :key="idx" :name="filename" @delete="onDelete(filename)"/>
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
    name: 'ImageBox',
    components: {
        TextBox
    },
    props: {
        required: Boolean
    },
    data() {
        return {
            filenames: [],
            images: []
        }
    },
    methods: {
        onDelete(name) {
            //이미지 목록 제거
            const idx = this.searchImage(name);
            console.log(idx);
            if(idx != -1) {
                this.images.splice(idx, 1);
                this.filenames.splice(idx, 1);
            }

            this.fileupload(this.images);
        },
        onCreate(event) {
            const imgs = event.target.files;
            //같은 파일을 제외하고 목록에 추가
            imgs.forEach((img) => {
                const idx = this.searchImage(img.name);
                if(idx == -1) {
                    this.images.push(img);
                    this.filenames.push(img.name);
                }
            });

            this.fileupload(this.images);
        },
        searchImage(key) {
            let result = -1;
            this.filenames.forEach((name, index) => {
                if(name === key) {
                    console.log(index);
                    result = index;
                }
            });
            return result;
        }
    },
    setup(props, { emit }) {
        const fileupload = (img) => {
            emit('fileupload', img);
        }
        return { fileupload };
    }
}
</script>
<style scoped>
    .live-subtitle {
        width: 80px;
    }
    .live-contents {
        width: 100%;
    }
    .thumbnail-file-box {
        flex: 1;
        border: 1px solid #dedede;
        border-radius: 8px;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        padding-left: 12px;
        padding-top: 8px;
    }
    .white-beige-btn {
        border: 1px solid #CFC098;
        color: #CFC098;
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