<template>
  <div class="inputForm">
    <h1>{{ msg }}</h1>
    <el-form
      :model="loginForm"
      status-icon
      :rules="rules"
      ref="loginForm"
      label-width="120px"
      class="loginForm"
    >
      <el-form-item label="ログインID" prop="loginId">
        <el-input type="loginId" v-model="loginForm.loginId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="パスワード" prop="password">
        <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-button type="primary" @click="submitForm('loginForm')">ログイン</el-button>
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
  name: "HelloWorld",
  props: {
    msg: String
  },
  el: "inputForm",
  data: function() {
    return {
      ok: false,
      error: false,
      loginForm: {
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-form.loginForm {
  padding-top: 2%;
  padding-left: 20%;
  padding-right: 20%;
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
