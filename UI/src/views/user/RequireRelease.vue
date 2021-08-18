<template>
  <div>
    <Header/>
    <div style="display: flex;margin: 10px 0">
      <div style="width: 10%"/>
      <Usersheader/>
      <div style="  width: 1%"></div>

      <!--      内容区域-->

      <div style="flex: 1">

        <!--        头-->
        <div>
          <el-card style="text-align: center;height: 100px">
            <el-avatar size="large" >用户</el-avatar>
          </el-card>
        </div>
        <!--资料-->
        <div style="margin: 10px 0">
          <el-card>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="需求类型" prop="identity">
                <el-radio-group v-model="ruleForm.identity">
                  <el-radio label=1 value="1">发布商品</el-radio>
                  <el-radio label=0 value="0">求购商品</el-radio>
                </el-radio-group>
              </el-form-item>

              <div style="display: flex">
                <div style="width: 50%">
                  <el-form-item label="单位" prop="cpnname">
                    <el-input v-model="ruleForm.cpnname" style="width: 300px"></el-input>
                  </el-form-item>
                  <el-form-item label="签发人" prop="signer">
                    <el-input v-model="ruleForm.signer" style="width: 300px"></el-input>
                  </el-form-item>
                  <el-form-item label="煤种" prop="coaltype">
                    <el-select v-model="ruleForm.coaltype" placeholder="请选择煤种">
                      <el-option v-for="(item) in coaltype" :label="item" :value="item"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="采购数量" prop="amount">
                    <el-input-number min="0" max="1000000" step="100" v-model="ruleForm.amount"></el-input-number>
                  </el-form-item>
                </div>
                <div>
                  <el-form-item label="运输方式" prop="transportation">
                    <el-select v-model="ruleForm.transportation">
                      <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="交货地点" prop="deliveryplace">
                    <el-input v-model="ruleForm.deliveryplace" style="width:200px"></el-input>
                  </el-form-item>
                  <el-form-item label="结算方式" prop="clearing">
                    <el-select v-model="ruleForm.clearing">
                      <el-option value="单次" label="单次"/>
                      <el-option value="分期" label="分期"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="验收方式" prop="acceptance">
                    <el-select v-model="ruleForm.acceptance">
                      <el-option value="线上" label="线上"/>
                      <el-option value="线下" label="线下"/>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="结算付款方式" prop="payment">
                    <el-select v-model="ruleForm.payment">
                      <el-option value="现金结算" label="现金结算"/>
                      <el-option value="转账结算" label="转账结算"/>
                    </el-select>
                  </el-form-item>

                </div>
              </div>
              <el-form-item label="需缴保证金" prop="ispaydeposit">
                <el-switch v-model="ruleForm.ispaydeposit"></el-switch>
              </el-form-item>
              <div  style="display: flex">
                <div style="width: 50%">
                  <el-form-item label="保证金单价" prop="price">
                    <el-input-number min="0" max="9999999" step="100" v-model="ruleForm.price"></el-input-number>
                  </el-form-item>
                  <el-form-item label="收到基低位发热量" prop="qnetar">
                    <el-input-number min="0" max="10000" step="100" v-model="ruleForm.qnetar"></el-input-number>
                  </el-form-item>
                  <el-form-item label="收到基全硫" prop="start">
                    <el-input-number min="0" max="10000" step="100" v-model="ruleForm.start"></el-input-number>
                  </el-form-item>
                  <el-form-item label="全水分(%)" prop="m">
                    <el-input-number min="0" max="100" v-model="ruleForm.m"></el-input-number>
                  </el-form-item>
                  <el-form-item label="收到基灰分(%)" prop="rba">
                    <el-input-number min="0" max="100" v-model="ruleForm.rba"></el-input-number>
                  </el-form-item>
                  <el-form-item label="收到基挥发分最低" prop="lrbvc">
                    <el-input-number min="0" max="100" v-model="ruleForm.lrbvc"></el-input-number>
                  </el-form-item>
                  <el-form-item label="收到基挥发分最高" prop="hrbvc">
                    <el-input-number min="0" max="100" v-model="ruleForm.hrbvc"></el-input-number>
                  </el-form-item>
                  <el-form-item label="空干基水分" prop="mad">
                    <el-input-number min="0" max="100" v-model="ruleForm.mad"></el-input-number>
                  </el-form-item>
                  <el-form-item label="空干基全硫" prop="stad">
                    <el-input-number min="0" max="100" v-model="ruleForm.stad"></el-input-number>
                  </el-form-item>
                </div>

                <div style="flex: 1">
                  <el-form-item label="空干基挥发分最低" prop="ladbv">
                    <el-input-number min="0" max="100" v-model="ruleForm.ladbv"></el-input-number>
                  </el-form-item>
                  <el-form-item label="空干基挥发分最高" prop="hadbv">
                    <el-input-number min="0" max="100" v-model="ruleForm.hadbv"></el-input-number>
                  </el-form-item>
                  <el-form-item label="干基高位发热量" prop="qgrd">
                    <el-input-number min="0" max="100" v-model="ruleForm.qgrd"></el-input-number>
                  </el-form-item>
                  <el-form-item label="干基全硫" prop="std">
                    <el-input-number min="0" max="100" v-model="ruleForm.std"></el-input-number>
                  </el-form-item>
                  <el-form-item label="干燥无灰色基挥发分最低" prop="ldwgbv">
                    <el-input-number min="0" max="100" v-model="ruleForm.ldwgbv"></el-input-number>
                  </el-form-item>
                  <el-form-item label="干燥无灰基挥发分最高" prop="hdwgbv">
                    <el-input-number min="0" max="100" v-model="ruleForm.hdwgbv"></el-input-number>
                  </el-form-item>
                  <el-form-item label="粒度" prop="granularity">
                    <el-input-number min="0" max="100" v-model="ruleForm.granularity"></el-input-number>
                  </el-form-item>
                  <el-form-item label="灰熔点" prop="hg">
                    <el-input-number min="0" max="100" v-model="ruleForm.hg"></el-input-number>
                  </el-form-item>
                  <el-form-item label="哈氏可磨系数" prop="st">
                    <el-input-number min="0" max="100" v-model="ruleForm.st"></el-input-number>
                  </el-form-item>
                </div>
              </div>



              <el-form-item label="备注" prop="comment">
                <el-input type="textarea" v-model="ruleForm.comment"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div>

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
import Cookies from 'js-cookie'
import {getRequest} from "@/utils/api";
import {postRequest} from "@/utils/api";
export default {
  name: "Release",
  components: {Footer, Usersheader, Header},
  data() {
    return {
      coaltype: ['新型煤', '动力煤', '无烟煤', '低质煤', '炼焦媒', '水煤浆', '焦煤', '煤泥'],
      options: [{
        value: '公路运输',
        label: '公路运输'
      }, {
        value: '铁路运输',
        label: '铁路运输'
      }, {
        value: '水路运输',
        label: '水路运输'
      }],
      ruleForm:
          {
            acceptance: "线上",
            amount: 10000,
            clearing: "单次",
            coaltype: "新型煤",
            comment: "",
            cpnname: "",
            deliveryplace: "",
            granularity: 10,
            hadbv: 10,
            hdwgbv: 10,
            hg: 10,
            hrbvc: 10,
            identity: 0,
            ispaydeposit: 10,
            ladbv: 10,
            ldwgbv: 10,
            lrbvc: 10,
            m: 10,
            mad: 10,
            no: Math.random()+432+Math.random()*7,
            payment: "现金结算",
            price: 100,
            //用户自己的id
            proposer: "",
            qgrd: 5,
            qnetar: 10,
            rba: 10,
            shipendtime: new Date(),
            shipstarttime: new Date(),
            signer: "",
            st: 10,
            stad: 10,
            start: 10,
            starttime: new Date(),
            status: 0,
            std: 0,
            transportation: "铁路运输"
          },

      rules: {
        // cpnname: [
        //   {required: true, message: '请输入公司名称', trigger: 'blur'},
        //   {min: 5, max: 50, message: '长度在 5 到 50 个字符', trigger: 'blur'}
        // ],
        // region:
        //     [
        //       {required: true, message: '请选择活动区域', trigger: 'change'}
        //     ],
        // deliveryplace:
        //     [
        //       {required: true, message: '请输入交货地点', trigger: 'blur'}
        //     ],
        // signer:[
        //   {required: true, message: '请输入签发人', trigger: 'blur'}
        // ],


        // resource:
        //     [
        //       {required: true, message: '请选择活动资源', trigger: 'change'}
        //     ],
        // desc:
        //     [
        //       {required: true, message: '请填写活动形式', trigger: 'blur'}
        //     ],
      }
    }
        ;
  }
  ,
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("下面是form");
          this.ruleForm.proposer=(Number)(Cookies.get('userId'));
          console.log("卖家："+this.ruleForm.proposer);
          this.ruleForm.ispaydeposit=(Number)(this.ruleForm.ispaydeposit);
          this.ruleForm.identity=(Number)(this.ruleForm.identity);
          console.log(this.ruleForm);

          postRequest("/list/add", this.ruleForm).then((resp) => {
            if (resp) {
              console.log("insert successful");
              console.log(resp.obj);
              this.$router.push("/sell");
            }
          });

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
    ,
    resetForm(formName) {
      this.$refs[formName].resetFields();
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
