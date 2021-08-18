<template>
  <div>
    <!--    导航栏-->
    <globalheader />
    <div class="grad">
      <el-form
        :model="loginForm"
        :rules="loginRules"
        ref="loginForm"
        class="form"
      >
        <h3 class="title">后台管理系统</h3>
        <el-form-item prop="username">
          <el-input
            type="text"
            v-model="loginForm.username"
            autocomplete="off"
            placeholder="账号"
            ><i slot="prefix" class="el-input__icon el-icon-user"></i>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="loginForm.password"
            autocomplete="off"
            placeholder="密码"
            style="margin-top: 10px"
          >
            <i slot="prefix" class="el-input__icon el-icon-lock"></i>
          </el-input>
        </el-form-item>

        <el-form-item prop="code">
          <el-input
            size="normal"
            type="text"
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="点击图片更换验证码"
            style="width: 55%; float: left; margin-top: 10px"
            @keydown.enter.native="submitLogin"
            ><i slot="prefix" class="el-input__icon el-icon-key"></i
          ></el-input>
          <img
            :src="vcUrl"
            @click="updateVerifyCode"
            alt=""
            style="cursor: pointer; float: left; margin-top: 10px"
          />
        </el-form-item>

        <el-form-item>
          <el-checkbox v-model="rememberMe" style="float: left; left: 30px">
            记住密码
          </el-checkbox>
          <el-checkbox style="float: left; left: 40px"> 自动登录 </el-checkbox>
        </el-form-item>

        <el-form-item style="margin-left: 40px" id="button">
          <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width: 35%"
            @click="submitLogin('loginForm')"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登录中...</span>
          </el-button>

          <el-button
            size="medium"
            type="primary"
            style="width: 35%"
            @click="submitRegister"
          >
            <span>注 册</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <Footer />
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Globalheader from "@/views/index/Globalheader";
import Cookies from "js-cookie";

export default {
  name: "login",
  components: { Globalheader, Footer },
  data() {
    return {
      loading: false,
      vcUrl: "/api/verifyCode",
      loginForm: {
        username: "",
        password: "",
        code: "",
      },
      rememberMe: false,
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入用户名" },
        ],
        password: [{ required: true, trigger: "blur", message: "请输入密码" }],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },
  methods: {
    updateVerifyCode() {
      this.vcUrl = "/api/verifyCode?time=" + new Date();
    },
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.postRequest("/doLogin", this.loginForm).then((resp) => {
            this.loading = false;
            if (resp) {
              this.$store.commit("INIT_CURRENTUSER", resp.obj);
              window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
              let path = this.$route.query.redirect;
              console.log(this.loginForm.username);
              //把用户账号存起来，方便不同组件获取
              Cookies.set("userId", this.loginForm.username);
              console.log(Cookies.get("userId"));
              if (this.loginForm.username === "admin") {
                this.$router.replace(
                  path == "/api" || path == undefined ? "/api/home" : path
                );
              } else {
                this.$router.replace("/api/mainpage");
              }
            } else {
              this.vcUrl = "/api/verifyCode?time=" + new Date();
              this.loginForm.code = "";
            }
          });
        } else {
          return false;
        }
      });
    },
    submitRegister() {
      this.$router.push("/api/UserRegister");
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
//@import "../assets/css/login.scss";
button {
  margin-right: 10%;
}
//.grad {
//  z: 2;
//  background-color: rgba(255, 255, 255, 0.5);
//  border-radius: 5%;
//  position: fixed;
//  top: 20%;
//  left: 70%;
//  height: 450px;
//  width: 350px;
//}
.form {
  position: absolute;
  padding: 5px 20px;
  border-radius: 5%;
  background-color: rgba(255, 255, 255, 0.5);
  height: 80%;
  z-index: 1;
  top: 0%;
  left: 68%;
  width: 280px;
  height: 400px;
  margin: 10% auto;
  img {
    height: 35px;
    margin-top: 4px;
    margin-left: 6px;
  }
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #666565;
}
</style>
