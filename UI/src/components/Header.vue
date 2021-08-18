<template>
  <div style="height: 40px;line-height: 40px;border-bottom: 2px solid #cccccc; display: flex">
    <div style="width:10%">
    </div>
    <div style="width: 200px">
      <el-link href="/mainpage" :underline="false">欢迎来到煤炭交易平台!</el-link>

    </div>
    <div style="flex: 1"></div>
    <div style="width: 100px">

      <router-link to="/login" style="text-decoration: none;color:rgb(96,98,102)">登录</router-link>
      <router-link to="/UserRegister" style="text-decoration: none;color:rgb(96,98,102);margin-left: 20px">注册</router-link>
    </div>
    <div style="width:10%">
    </div>
  </div>
</template>

<script>

export default {
  name: "Header"
}
</script>

<style scoped>

</style>
