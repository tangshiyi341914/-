<template>
  <div>
    <NewHeader/>
      <div class="box">
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">

        <el-button-group>
          <el-button  style="text-align: center;float: left;left: 85px" type="info" v-on:click="onUserRegister">用户注册</el-button>
          <el-button  style="text-align: center;float: left;left: 85px" type="success" v-on:click="onCompanyRegister">企业注册</el-button>
        </el-button-group>

        <el-form-item label="企业名称" prop="cpnname" style="margin-top: 10px">
          <el-input type="text" placeholder="请输入企业名称" v-model="form.cpnname"/>
        </el-form-item>

        <el-form-item label="法人代表" prop="legalperson">
          <el-input type="text" placeholder="请输入法人代表名称" v-model="form.legalperson"/>
        </el-form-item>

        <el-form-item label="注册地区" prop="regstarea">
          <el-input type="text" placeholder="请输入公司注册地区" v-model="form.regstarea"/>
        </el-form-item>

        <el-form-item label="注册资本" prop="regstcapital">
          <el-input type="text" placeholder="请输入公司注册资本" v-model="form.regstcapital"/>
        </el-form-item>

        <el-form-item label="公司邮箱" prop="cpnemail">
          <el-input type="email" placeholder="请输入公司邮箱" v-model="form.cpnemail"/>
        </el-form-item>

        <el-form-item label="公司简介" prop="introduction">
          <el-input type="text" placeholder="请输入公司简介" v-model="form.introduction"/>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              v-on:click="onSubmit('loginForm')"
              style="text-align: center;float:left;position:relative;left: 50px;"

          >确定提交</el-button>
        </el-form-item>

      </el-form>

      <el-dialog
          title="温馨提示"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
        <span>公司信息填写有误</span>
        <span slot="footer" class="dialog-footer">
        <el-button
            type="primary"
            @click="dialogVisible = false"
            style="text-align: center;float:left;position:relative;left: 50px;"
        >确定提交</el-button>
      </span>
      </el-dialog>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import {postRequest} from "@/utils/api";
import NewHeader from "@/views/index/NewHeader";
export default {
  name: "CompanyRegister",
  components: {NewHeader, Footer},
  data: function () {
    return {
      form: {
        cpnname: '',
        legalperson: '',
        regstarea: '',
        regstcapital: '',
        cpnemail: '',
        introduction: '无'//默认为 “ 无 ”
      },
      rules: {
        cpnname: [
          {required: true, message: '公司名称不可为空', trigger: 'blur'}
        ],
        legalperson: [
          {required: true, message: '法人代表不可为空', trigger: 'blur'}
        ],
        regstarea: [
          {required: true, message: '注册地区不可为空', trigger: 'blur'}
        ],
        regstcapital: [
          {required: true, message: '注册资本不可为空', trigger: 'blur'},

        ],
        cpnemail: [
          {required: true, message: '公司邮箱不可为空', trigger: 'blur'},
          {
            validator: function (rule, value, callback) {
              const reg = /^([a-zA-Z0-9]+[-_+]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
              if (value == '' || value == undefined || value == null) {
                callback();
              } else {
                if (!reg.test(value)) {
                  callback(new Error('请输入正确的邮箱'));
                } else {
                  callback();
                }
              }
            }, trigger: 'blur'
          }
        ]
      },

      // 对话框显示和隐藏
      dialogVisible: false
    }
  },
  methods: {
    onSubmit(formName) {
      // 为表单绑定验证功能
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.form);
          postRequest("/registerCompany", this.form).then((resp) => {
            if (resp) {
              console.log("insert successful");
              console.log(resp.obj);
              this.$router.push("/sell");
            }
          });
        } else {
          this.dialogVisible = true;
          return false;
        }
      });
    },
    onCompanyRegister() {
      this.$router.push("/CompanyRegister");
    },
    onUserRegister() {
      this.$router.push("/UserRegister");
    },
  }
}
</script>

<style lang="scss" scoped>
.login-box {
  border: 1px solid #DCDFE6;;
  background-color: #cccccc;
  z-index: 1;
  position: absolute;
  top: -5%;
  left: 65%;
  width: 330px  ;
  margin: 180px auto;
  padding: 30px 50px 20px 20px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  //box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
