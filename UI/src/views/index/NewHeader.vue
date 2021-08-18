<template>
  <div>
    <div>
      <!--    页头-->
      <!--      <Header/>-->
      <!--    导航栏-->
      <div style="display: flex">
        <div style="width: 10%;background-color: #545c64 "/>
        <div style="flex: 1">
          <el-menu
              :default-active="this.$route.path"
              class="el-menu-demo"
              mode="horizontal"
              router
              background-color="#545c64"
              text-color="#fff"
              @select="handleSelect"
              active-text-color="#ffd04b">
            <el-menu-item v-for="(item, i) in navList" :key="i" :index="item.name">
              {{ item.navItem }}
            </el-menu-item>
          </el-menu>
        </div>
        <div style="width: 10%;background-color: #545c64 "/>
      </div>
    </div>
    <div class="demo-image__placeholder">
      <div class="block">
        <el-image :src="src"/>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "NewHeader",
  data(){
    return{
      activeIndex:1,
      navList:[
        //  name 更换为要跳转网页的坐标
        { name: "/mainpage", navItem: "网站首页" },
        { name: "/sell", navItem: "煤炭商城" },
        { name: "/about", navItem: "关于我们" },
        { name: "/contact", navItem: "联系我们" },
      ],
      // src:'https://i.loli.net/2021/08/05/dAJRPIjhMFg9y5x.png'
      src:'https://z3.ax1x.com/2021/08/12/fdBoDJ.png'

    }
  },
  methods:{
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  }
}
</script>

