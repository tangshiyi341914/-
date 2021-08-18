<template>
  <div>
    <Header/>
    <div style="display: flex;margin: 10px 0">
      <div style="width: 10%"/>
      <Usersheader/>
      <div style="width: 1%"></div>

      <!--      内容区域-->

      <div style="flex: 1">

        <!--        头-->
        <div>
          <el-card style="text-align: center">
            <el-avatar size="100"> {{user.name}}</el-avatar>
          </el-card>
        </div>
        <!--资料-->
        <div style="margin: 10px 0">
          <el-card>
            <el-descriptions title="用户信息"
                             column="1"
            >
              <el-descriptions-item label="姓名">{{user.name}}</el-descriptions-item>
              <el-descriptions-item label="性别">{{user.gender}}</el-descriptions-item>
              <el-descriptions-item label="账号">{{user.username}}</el-descriptions-item>
              <el-descriptions-item label="用户ID">{{user.id}}</el-descriptions-item>
              <el-descriptions-item label="电话号码">{{user.phone}}</el-descriptions-item>
              <el-descriptions-item label="邮箱">{{user.email}}</el-descriptions-item>
              <el-descriptions-item label="地址">{{user.address}}</el-descriptions-item>
              <el-descriptions-item label="生日">{{user.birthday}}</el-descriptions-item>
              <el-descriptions-item label="从属企业">{{user.cpnName}}</el-descriptions-item>
            </el-descriptions>
            <el-button type="danger">
              编辑
            </el-button>
          </el-card>

          <el-form>

          </el-form>

        </div>

        <!--        公司资料-->
<!--        <div style="margin: 10px 0">-->
<!--          <el-card>-->
<!--            <el-descriptions title="企业信息"-->
<!--                             column="1"-->
<!--            >-->
<!--              <el-descriptions-item label="企业名称">成都信息工程大学</el-descriptions-item>-->
<!--              <el-descriptions-item label="法人代表">114514</el-descriptions-item>-->
<!--              <el-descriptions-item label="注册地区">成都</el-descriptions-item>-->
<!--              <el-descriptions-item label="注册资金">114514万元</el-descriptions-item>-->
<!--              <el-descriptions-item label="企业邮箱">1919810@qq.com</el-descriptions-item>-->
<!--              <el-descriptions-item label="企业简介">成都信息工程大学是一所停电又停水的双流一大学</el-descriptions-item>-->
<!--            </el-descriptions>-->
<!--            <el-button type="danger">-->
<!--              编辑-->
<!--            </el-button>-->
<!--          </el-card>-->
<!--        </div>-->
      </div>
      <div style="width: 10%"/>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Usersheader from "../index/UsersHeader";
import Header from "../../components/Header";
import Footer from "@/components/Footer";
import Cookies from 'js-cookie';
import {getRequest} from "../../utils/api";

export default {
  name: "profile",
  data() {
    return {
      user: null,
      user2:null
    };
  },
  components: {Footer, Usersheader, Header},

  methods: {
    initUser() {
      getRequest("/info").then((resp) => {
        if (resp) {
          this.user = resp;
          this.user2 = Object.assign({}, this.user);
          console.log(this.user);
          window.sessionStorage.setItem("user", JSON.stringify(resp));
          this.$store.commit("INIT_CURRENTUSER", resp);
        }
      });
    }
  },
  mounted() {
    this.initUser();
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
