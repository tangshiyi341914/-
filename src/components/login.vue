<template>
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
        >
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input> </el-form-item
      ><el-checkbox v-model="loginForm.rememberMe" style="margin-bottom: 5px">
        记住密码</el-checkbox
      >
      <el-form-item style="margin-bottom: 5px">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width: 100%"
          @click="submitForm('loginForm')"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          size="medium"
          style="width: 100%"
          @click="resetForm('loginForm')"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        rememberMe: false,
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "用户名不能为空" },
        ],
        password: [
          { required: true, trigger: "blur", message: "密码不能为空" },
        ],
      },
      loading: false,
    };
  },
  methods: {
    submitForm() {
      this.$refs.loginName.validate((valid) => {
        this.loading = true;
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import "../assets/css/login.scss";
</style>
