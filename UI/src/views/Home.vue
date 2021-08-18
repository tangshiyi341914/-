<template  >
  <div>
    <el-container style="height: 800px; #eee">
      <el-header style="font-size: 20px">
        <div class="title-l">煤炭交易系统</div>
        <div class="title-r">
          <el-avatar
            src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"
            class="svg"
          ></el-avatar>
          <el-dropdown @command="commandHandler" trigger="click">
            <i
              class="el-icon-caret-bottom"
              style="font-size: 35px; margin-top: 10px"
            ></i>
            <el-dropdown-menu slot="dropdown" class="drop">
              <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
              <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <div style="float: right">
            <span>{{ user.name }}</span>
          </div>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu router unique-opened>
            <el-submenu
              v-for="(item, index) in routes"
              :index="index + ''"
              :key="index"
            >
              <template slot="title">
                <i
                  style="color: #409eff; margin-right: 5px"
                  :class="item.iconCls"
                ></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item
                :index="child.path"
                v-for="(child, indexj) in item.children"
                :key="indexj"
              >
                {{ child.name }}
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb
            separator-class="el-icon-arrow-right"
            v-if="this.$router.currentRoute.path != '/home'"
          >
            <el-breadcrumb-item :to="{ path: '/home' }"
              >首页</el-breadcrumb-item
            >
            <el-breadcrumb-item>{{
              this.$router.currentRoute.name
            }}</el-breadcrumb-item>
          </el-breadcrumb>
          <div
            class="homeWelcome"
            v-if="this.$router.currentRoute.path == '/home'"
          >
            欢迎来买煤炭！
          </div>
          <router-view class="homeRouterView" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  name: "home",
  data() {
    return {
      item: "",
      index: "",
    };
  },
  computed: {
    routes() {
      return this.$store.state.routes;
    },
    user() {
      return this.$store.state.currentUser;
    },
  },
  methods: {
    commandHandler(cmd) {
      if (cmd == "logout") {
        this.$confirm("此操作将注销登录, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            this.getRequest("/logout");
            window.sessionStorage.removeItem("user");
            this.$store.commit("initRoutes", []);
            this.$router.replace("/");
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消操作",
            });
          });
      } else if (cmd == "userinfo") {
        this.$router.push("/userinfo");
      }
    },
  },
};
</script>

<style scoped lang="scss">
@import "../assets/css/home.scss";
</style>
