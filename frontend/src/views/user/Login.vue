<template>
  <div class="lagin-page">
    <center>
      <div id="login-div">
            <img src="@/assets/login-ill.png" width="175" class="login-ill"/>
      <form @submit.prevent="upload">
        <table class="login-table">

          <tr>
            <span>아이디</span>
          </tr>
          <tr>
            <input v-model="user.userId" type="text" name="userEmail" placeholder="아이디를 입력하세요" />
          </tr>

          <tr>
            <span>비밀번호</span>
          </tr>
          <tr>
            <input v-model="user.password" type="password" name="password" placeholder="비밀번호를 입력하세요"/>
          </tr>

          <tr>
            <td><input id="login-button" type="submit" value="로그인" /></td>
          </tr>
        </table>
      </form>
      <hr color="#F4F4F4">
      <div>
        <span style="color: #dedede;">회원가입 하시겠습니까?</span>
        <span @click="$router.push('SignUp')" class="signup-btn">
          회원가입
        </span>
      </div>
        </div>
    </center>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: 'Login',
  data() {
    return {
      user: {
        userId: '',
        password: '',
      },
    }
  },
  methods: {
    ...mapActions(["login"]),
    upload() {
      this.login(this.user);
    },
  }
};
</script>

<style scoped>
.login-page {
  align-content: center;
}
#login-div {
  margin-top: 100px;
  width: 500px;
  padding-bottom: 50px;
  border-radius: 30px;
  box-shadow : 2px 2px 4px lightgrey;
}
.login-ill {
  margin-top: -45px;
}
.login-table {
  margin-top: 30px;
  width: 80%;
  align-content: center;
}
#login-button{
  background-color: #dacca6;
  margin-top: 25px;
  color: white;
}
#login-button:hover {
  cursor: pointer;
}
tr {
  height: 30px;
  width: 100%;
}
input {
  height: 47px;
  width: 100%;
  margin-bottom: 20px;
  background-color: #F4F4F4;
  border: none;
  border-radius: 15px;
  padding-left: 10px;
}
* {
  font-size: 15px;
}
hr {
  margin: 50px 0;
  width: 350px;
}
.signup-btn {
  cursor: pointer;
}

</style>
