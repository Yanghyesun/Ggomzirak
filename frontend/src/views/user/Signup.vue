<template>
  <div class="lagin-page">
    <center>
      <div id="signup-div">
        <img src="@/assets/login-ill.png" width="175" class="login-ill" />

        <Form @submit="checkdup" :validation-schema="schema">
          <table class="signup-table">
            <tr>
              <span>아이디</span>
            </tr>
            <tr>
              <td colspan="2">
                <Field
                  v-model="user.userId"
                  name="userId"
                  type="text"
                  placeholder="아이디를 입력하세요"
                />
                <ErrorMessage name="userId" style="color: #e88989" />
              </td>
            </tr>

            <tr>
              <span>비밀번호</span>
            </tr>
            <tr>
              <td colspan="2">
                <!-- <input v-model="user.password" type="password" name="password" /> -->
                <Field
                  v-model="user.password"
                  name="password"
                  type="password"
                  placeholder="비밀번호를 입력하세요"
                />
                <ErrorMessage name="password" style="color: #e88989" />
              </td>
            </tr>

            <tr>
              <td colspan="2">
                <span>비밀번호 확인</span>
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <Field
                  v-model="checkpwd"
                  name="checkpwd"
                  type="password"
                  placeholder="비밀번호 확인을 입력하세요"
                />
                <ErrorMessage name="checkpwd" style="color: #e88989" />
              </td>
            </tr>

            <tr>
              <td colspan="2">
                <span>닉네임</span>
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <Field
                  v-model="user.name"
                  name="name"
                  type="text"
                  placeholder="닉네임을 입력하세요"
                />
                <ErrorMessage name="name" style="color: #e88989" />
              </td>
            </tr>

            <tr>
              <span>이메일</span>
            </tr>
            <tr>
              <td colspan="2">
                <!-- <input v-model="user.email" type="text" name="password" /> -->
                <Field
                  v-model="user.email"
                  name="email"
                  type="text"
                  placeholder="이메일을 입력하세요"
                />
                <ErrorMessage name="email" style="color: #e88989" />
              </td>
            </tr>

            <tr>
              <td width="80%">
                <input id="signup-button" type="submit" @onClick="upload" value="회원가입" />
              </td>
              <td
                class="reset-btn"
                width="20%"
                style="text-align: center; padding-top: 25px; color: #ababab"
                @onClick="reset"
              >
                취소
              </td>
            </tr>
          </table>
        </Form>
      </div>
    </center>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from 'vee-validate';
import * as yup from 'yup';
import $axios from 'axios';

export default {
  name: 'SignUp',
  components: {
    Form,
    Field,
    ErrorMessage,
  },

  setup() {
    const nameRegex = /^[A-Za-z0-9+]+$/;
    const schema = yup.object().shape({
      userId: yup
        .string()
        .required()
        .min(5, '5글자 이상 입력해주세요')
        .matches(nameRegex, '영어로 입력해주세요'),
      name: yup.string().required().min(1, '1글자 이상 입력해주세요'),
      email: yup.string().required().email('이메일 형식으로 입력해주세요'),
      password: yup.string().required().min(4, '4글자 이상 입력해주세요'),
      checkpwd: yup.string().oneOf([yup.ref('password'), null], '비밀번호가 일치하지 않습니다'),
    });
    return { nameRegex, schema };
  },

  data() {
    return {
      user: {
        userId: '',
        name: '',
        password: '',
        email: '',
      },
      checkpwd: '',
    };
  },
  methods: {
    upload() {
      const formData = new FormData();
      formData.append('userid', this.user.userId);
      formData.append('password', this.user.password);
      formData.append('name', this.user.name);
      formData.append('email', this.user.email);

      $axios
        .post('/user/reg', formData, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then(() => {
          alert('회원가입에 성공하였습니다. 로그인 해주세요!');
          this.$router.push('/login');
        })
        .catch(() => {
          alert('회원가입에 실패하였습니다.');
        });
    },
    // Validator function
    isRequired(value) {
      return value ? true : 'This field is required';
    },
    checkdup() {
      $axios
        .get('/user/check/' + this.user.userId)
        .then(() => {
          this.upload();
        })
        .catch(() => {
          alert('이미 사용중인 ID 입니다. 다른 아이디를 사용해주세요.');
          this.user.userId = '';
        });
    },
  },
};
</script>

<style scoped>
.signup-page {
  align-content: center;
}

.login-ill {
  margin-top: -45px;
}
#signup-div {
  margin-top: 100px;
  width: 500px;
  padding-bottom: 30px;
  border-radius: 30px;
  box-shadow: 2px 2px 4px lightgrey;
}
.signup-table {
  margin-top: 30px;
  width: 80%;
  align-content: center;
}
#signup-button {
  background-color: #dacca6;
  margin-top: 45px;
  color: white;
}

#signup-button:hover {
  cursor: pointer;
}
tr {
  height: 25px;
  width: 100%;
}
input {
  height: 42px;
  width: 100%;
  margin-bottom: 15px;
  background-color: #f4f4f4;
  border: none;
  border-radius: 15px;
  padding-left: 10px;
}
.reset-btn:hover {
  cursor: pointer;
}
* {
  font-size: 15px;
}
</style>
