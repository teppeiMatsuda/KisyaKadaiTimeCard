<template>
  <div class="inputForm">
    <h1>{{ msg }}</h1>
    <el-form
      :model="loginForm"
      status-icon
      :rules="rules"
      label-width="120px"
      ref="loginForm"
      class="loginForm"
    >
      <el-form-item label="ログインID" prop="loginId">
        <el-input type="loginId" v-model="loginForm.loginId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="パスワード" prop="password" id="password-div">
        <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item id="signUpLinkDiv">
        <p id="signUpLink">
          Don't have an Account?
          <router-link to="/signup">SignUp</router-link>
        </p>
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
import axios from 'axios';
Vue.use(ElementUI, { locale });

// CSRFトークン取得
const CSRF_TOKEN = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`))[1];
const instance = axios.create({
  headers: { "X-CSRF-TOKEN": CSRF_TOKEN }
});
export const AXIOS = instance;

export default {
  name: "LoginComponent",
  props: {
    msg: String
  },
  data: function() {
    return {
      ok: false,
      error: false,
      loginForm: {
        loginId: "axios45",
        password: ""
      },
      rules: {
        loginId: [
          { required: true, message: "ログインIDを入力してください" },
          { max: 16, message: "16文字以内で入力してください。" }
        ],
        password: [
          { required: true, message: "パスワードを入力してください。" },
          { max: 30, message: "30(暫定)文字以内で入力してください。" }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!(まだ))");
          const data = new FormData();
          data.append("loginId",this.loginForm.loginId);
          data.append("password",this.loginForm.password);
          console.warn(this.loginForm.loginId + 'ログインIDですか？');
          console.warn(this.serverPass + ':サーバーパスは何？');
         
          const params = new URLSearchParams();
          // spring securityのログインはformデータを送る必要がある。
          // 無加工だとjsonで送ってしまうので加工するしないといけない。
          //(const data = new FormData()
          // AXIOS.post('/api/login', data)ではダメだったのでまた違うっぽい(？))
          params.append('loginId', this.loginForm.loginId);
          params.append('password', this.loginForm.password);
          AXIOS.post('/api/login', params
          //
          // , {transformRequest: [function(data) {
          //     const formData = new FormData();
          //     formData.append("loginId",this.loginForm.loginId);
          //     formData.append("password",this.loginForm.password);
          //     return formData;
          //   }]
          // })
          )
          .then((response) => {
            console.warn(response);
          })
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
  padding-left: 30%;
  padding-right: 30%;
}

#signUpLink {
  padding: 0px;
  margin: 2px;
  text-align: right;
  color: rgb(192, 190, 186);
}
#signUpLinkDiv.el-form-item__content{
  vertical-align: text-bottom;
}
#signUpLinkDiv{
  margin-top: 10px;
}
#password-div {
  margin-bottom: 0px;
}

.el-button--primary {
  background-color: #41b883;
  border-color: #41b883;
}

.el-form-item {
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
