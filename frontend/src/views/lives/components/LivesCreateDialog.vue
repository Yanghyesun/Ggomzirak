<template>
    <div class="lives-create-dialog">
        <div class="lives-create-box">
            <p class="live-title">라이브 생성</p>
            <form>
                <div class="title-label lb">
                    <span class="live-subtitle"><span class="required">*</span>제목</span>
                    <div class="live-contents">
                        <input type="text" placeholder="제목을 입력해주세요" class="title" v-model="title"/>
                    </div>
                </div>
                <div class="contents-label lb">
                    <span class="live-subtitle"><span class="required">*</span>내용</span>
                    <div class="live-contents">
                        <input type="text" placeholder="내용을 입력해주세요" class="contents"  v-model="contents"/>
                    </div>
                </div>
                <ThumbnailBox @fileupload="fileupload" @fileDelete="fileDelete" :required="true"/>
                <div style="margin-top: 20px">
                    <label style="font-size: 16px;">
                        <input type="checkbox" value="true" v-model="isHint" class="cb-hint"/>
                        훈수요청하기
                    </label>
                </div>
                <div class="create-btn-box">
                    <div class="beige-btn" @click="submit">생성</div>
                    <div class="gray-btn" @click="cancel">취소</div>
                </div>
            </form>
        </div>
    </div>
</template>
<script>
import ThumbnailBox from '../../common/ThumbnailBox.vue'
import axios from 'axios';
import check from '../../../common/check.js';
export default {
    name: 'LivesCreateDialog',
    components: {
        ThumbnailBox
    },
    data() {
        return {
            isHint: false,
            title: '',
            contents: '',
            thumbnail: undefined,
        }
    },
    methods: {
        submit() {
            const userId = localStorage.getItem('userId');
            console.log(userId);

            let myheaders = '';
            if (check() == 0) {
                myheaders = localStorage.getItem('accessToken');
            } else {
                myheaders = localStorage.getItem('refreshToken');
            }
            if(userId != null) {
                if(this.title === '' || this.contents === '' || this.thumbnail === undefined) {
                    alert("제목 및 내용, 썸네일을 모두 입력해주세요!");
                } else {
                    const formData = new FormData();
                    formData.append('userId', userId);
                    formData.append('title', this.title);
                    formData.append('contents', this.contents);
                    formData.append('thumbnail', '썸네일');
                    formData.append('thumbnailFile', this.thumbnail);
                    formData.append('type', this.isHint ? 2 : 1);
                    
                    axios.post(
                        '/live',
                        formData,
                        { headers: {"Content-Type" : "multipart/form-data", "jwt-auth-access-token" : myheaders} }
                    ).then((response) => {
                        console.log(response.data);
                        this.$router.push('/lives/broadcast/' + response.data);
                    }).catch(() => {
                        alert("라이브 생성에 실패했습니다.");
                        console.log("라이브 생성 통신 실패");
                    });
                }
            } else {
                alert("로그인을 먼저 해주세요!");
            }
        },
        fileupload(data) {
            this.thumbnail = data;
        },
        fileDelete() {
            this.thumbnail = null;
        }
    },
    setup(props, { emit }) {
        

        const cancel = () => {
            emit('closeDialog')
        };


        return { cancel };
    },
}
</script>
<style scoped>
    .lives-create-dialog {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: #00000062;
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 999;
    }
    .lives-create-box {
        background-color: #ffffff;
        width: 600px;
        padding: 32px 32px;
        border-radius: 5px;
    }
    form {
        display: flex;
        flex-direction: column;
        font-size: 18px;
    }
    .title, .contents {
        border: 1px solid #dedede; 
        padding: 18px 14px;
        border-radius: 8px;
        width: 95%;
        font-size: 16px;
    }
    .lb {
        display: flex;
        margin-bottom: 24px;
        align-items: center;
    }
    .live-title {
        font-size: 26px;
        margin-bottom: 24px;
    }
    .live-subtitle {
        width: 80px;
    }
    .live-contents {
        width: 100%;
    }
    .create-btn-box {
        display: flex;
        justify-content: flex-end;
    }
    .beige-btn {
        background-color: #CFC098;
        color: #FFFFFF;
        padding: 14px 28px;
        border-radius: 8px;
        margin-right: 8px;
    }
    .gray-btn {
        border: 1px solid #dedede;
        color: #dedede;
        padding: 14px 28px;
        border-radius: 8px;
    }
    .beige-btn:hover {
        cursor: pointer;
    }
    .gray-btn:hover {
        cursor: pointer;
    }
    .required {
    color: #cfc098;
        font-size: 18px;
        margin-right: 8px;
    }
</style>