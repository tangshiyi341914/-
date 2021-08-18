<template>
  <div>
    <NewHeader/>
    <div class="box">
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="90px" class="login-box">
        <el-button-group>
          <el-button style="text-align: center;float: left;left: 85px" type="success" v-on:click="onUserRegister">用户注册
          </el-button>
          <el-button style="text-align: center;float: left;left: 85px" type="info" v-on:click="onCompanyRegister">企业注册
          </el-button>
        </el-button-group>

        <el-form-item label="账号" prop="username" style="margin-top: 20px">
          <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
        </el-form-item>

        <el-form-item label="本人姓名" prop="name">
          <el-input type="text" placeholder="请输入姓名" v-model="form.name"/>
        </el-form-item>

        <el-form-item label="本人电话" prop="phone">
          <el-input type="int" placeholder="请输入电话号码" v-model="form.phone"/>
        </el-form-item>

        <el-form-item label="所属公司" prop="cpnName">
          <el-input type="text" placeholder="请输入公司名称" v-model="form.cpnName"/>

        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input type="text" placeholder="请输入电话号码" v-model="form.email"/>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input type="text" placeholder="请输入地址" v-model="form.address"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择">
            <el-option  value="男" :label="男"></el-option>
            <el-option  value="女" :label="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker
              v-model="form.birthday"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              v-on:click="onSubmit('loginForm')"
              style="text-align: center;float:left;position:relative;left: 50px;"
          >确定提交
          </el-button>
        </el-form-item>
      </el-form>

      <el-dialog
          title="温馨提示"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
        <span>个人信息填写有误</span>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
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
  name: "UserRegister",
  components: {NewHeader, Footer},
  data() {
    return {
      form: {
        username: '',
        password: '',
        name: '',
        phone: '',
        cpnName: '',
        email: '',
        gender: '',
        address: '',
        birthday: '',
        enabled:1
      },
      rules: {
        username: [
          {required: true, message: '账号不可为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不可为空', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '姓名不可为空', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号码', trigger: 'blur'},
          {
            validator: function (rule, value, callback) {
              if (/^1[34578]\d{9}$/.test(value) == false) {
                callback(new Error("请输入正确的手机号"));
              } else {
                callback();
              }
            }, trigger: 'blur'
          },
        ],
        cpnName: [
          {min:3,max:30,required: true, message: '公司名称长度在30-30之间', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '地址不可为空', trigger: 'blur'}
        ],
        gender: [
          {required: true, message: '性别不可为空', trigger: 'change'}
        ],
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
          postRequest("/registerUser", this.form).then((resp) => {
            if (resp) {
              console.log("insert successful");
              console.log(resp.obj);
              this.$router.push("/mainpage");
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
  },
}
</script>

<style lang="scss" scoped>
.login-box {
  border: 1px solid #DCDFE6;
  background-color: #cccccc;
  position: absolute;
  z-index: 1;
  top: -5%;
  left: 65%;
  width: 330px;
  margin: 180px auto;
  padding: 30px 50px 20px 20px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}

</style>
