<template>
  <div class="inputForm">
    <h1>{{ msg }}</h1>
    <el-form
      :model="signUpForm"
      status-icon
      :rules="rules"
      label-width="150px"
      ref="signUpForm"
      class="sign_up_form"
    >
      <el-form-item label="ユーザー名" prop="userName">
        <el-input type="userName" v-model="signUpForm.userNamee" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="ログインID" prop="loginId">
        <el-input type="loginId" v-model="signUpForm.loginId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="パスワード" prop="password">
        <el-input type="password" v-model="signUpForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="パスワード(確認)" prop="password">
        <el-input type="password" v-model="signUpForm.passwordConfirm" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item id="loginLinkDiv">
        <p id="loginLink">
          Have an account? 
          <router-link to="/">Login</router-link>
        </p>
      </el-form-item>
      <el-button type="primary" @click="submitForm('signUpForm')">登録</el-button>
    </el-form>
  </div>
</template>


<script>
import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import locale from "element-ui/lib/locale/lang/ja";
Vue.use(ElementUI, { locale });

export default {
  name: "SignUpComponent",
  props: {
    msg: String
  },
  data: function() {
    return {
      ok: false,
      error: false,
      signUpForm: {
        loginId: "",
        password: ""
      },
      rules: {
        loginId: [
          { required: true, message: "ログインIDを入力してください" },
          { max: 16, message: "16文字以内で入力してください。" },
        ],
        password: [
          { required: true, message: "パスワードは必ず入力してください。" },
          { max: 30, message: "30(暫定)文字以内で入力してください。" }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          alert("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>
.el-form.sign_up_form {
  padding-top: 2%;
  padding-left: 30%;
  padding-right: 30%;
}

#loginLink {
  padding: 0px;
  margin: 2px;
  text-align: right;
  color: rgb(192, 190, 186);
}
#loginLinkDiv.el-form-item__content{
  vertical-align: text-bottom;
}

.el-button--primary{
  background-color: #41B883;
  border-color: #41B883;
  /* border-color:rgb(94, 107, 109); */
}

.el-form-item{
  margin-right: 60px;
}

h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
