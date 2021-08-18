<template>
  <div style="min-height: 600px">
    <el-menu
        :default-active="this.$route.path"
        class="el-menu-demo"
        mode="vertical"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        router
        active-text-color="#ffd04b"
        >
      <el-menu-item v-for="(item, i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>

export default {
  name: "Usersheader",
  data(){
    return{
      navList:[
        //  name 更换为要跳转网页的坐标
        { name: "/mainpage", navItem: "交易大厅" },
        { name: "/profile", navItem: "用户资料" },
        { name: "/require", navItem: "需求发布" },
        { name: "/records", navItem: "交易记录" },
      ]
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
