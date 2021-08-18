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
            <el-avatar size="100"> 野兽前辈 </el-avatar>
          </el-card>
        </div>
        <!--订单-->

        <div style="display: flex;margin: 10px 0">
          <div style="width: 10%;"></div>

          <el-table
              :data="records"
              style="width: 100%">
            <el-table-column type="expand">
              <template #default="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="挂牌者身份：">
                    <span>{{ props.row.identity }}</span>
                  </el-form-item>
                  <el-form-item label="挂牌单位：">
                    <span>{{ props.row.cpnname }}</span>
                  </el-form-item>
                  <el-form-item label="单据编号：">
                    <span>{{ props.row.no }}</span>
                  </el-form-item>
                  <el-form-item label="申请人：">
                    <span>{{ props.row.proposer }}</span>
                  </el-form-item>
                  <el-form-item label="签发人：">
                    <span>{{ props.row.signer }}</span>
                  </el-form-item>
                  <el-form-item label="申请时间：">
                    <span>{{ props.row.starttime }}</span>
                  </el-form-item>
                  <el-form-item label="交货时间（起始）：">
                    <span>{{ props.row.shipstarttime }}</span>
                  </el-form-item>
                  <el-form-item label="交货时间（结束）：">
                    <span>{{ props.row.shipendtime }}</span>
                  </el-form-item>
                  <el-form-item label="煤种：">
                    <span>{{ props.row.coaltype }}</span>
                  </el-form-item>
                  <el-form-item label="采购数量：">
                    <span>{{ props.row.amount }}</span>
                  </el-form-item>
                  <el-form-item label="运输方式：">
                    <span>{{ props.row.transportation }}</span>
                  </el-form-item>
                  <el-form-item label="交货地点：">
                    <span>{{ props.row.deliveryplace }}</span>
                  </el-form-item>
                  <el-form-item label="结算方式：">
                    <span>{{ props.row.clearing }}</span>
                  </el-form-item>
                  <el-form-item label="验收方式：">
                    <span>{{ props.row.acceptance }}</span>
                  </el-form-item>
                  <el-form-item label="结算付款方式：">
                    <span>{{ props.row.payment }}</span>
                  </el-form-item>
                  <el-form-item label="是否要求缴纳保证金：">
                    <span>{{ props.row.ispaydeposit }}</span>
                  </el-form-item>
                  <el-form-item label="保证金单价：">
                    <span>{{ props.row.price }}</span>
                  </el-form-item>
                  <el-form-item label="热值（KCal/kg）：">
                    <span>{{ props.row.qnetar }}</span>
                  </el-form-item>
                  <el-form-item label="收到基全硫：">
                    <span>{{ props.row.start }}</span>
                  </el-form-item>
                  <el-form-item label="全水分：">
                    <span>{{ props.row.m }}</span>
                  </el-form-item>
                  <el-form-item label="收到基灰分：">
                    <span>{{ props.row.rba }}</span>
                  </el-form-item>
                  <el-form-item label="收到基挥发分最低：">
                    <span>{{ props.row.lrbvc }}</span>
                  </el-form-item>
                  <el-form-item label="收到基挥发最高：">
                    <span>{{ props.row.hrbvc }}</span>
                  </el-form-item>
                  <el-form-item label="空干基水分：">
                    <span>{{ props.row.mad }}</span>
                  </el-form-item>
                  <el-form-item label="空干基全硫：">
                    <span>{{ props.row.stad }}</span>
                  </el-form-item>
                  <el-form-item label="空干基挥发分最低：">
                    <span>{{ props.row.ladbv }}</span>
                  </el-form-item>
                  <el-form-item label="空干基挥发分最高：">
                    <span>{{ props.row.hadbv }}</span>
                  </el-form-item>
                  <el-form-item label="干基高位发热量：">
                    <span>{{ props.row.qgrd }}</span>
                  </el-form-item>
                  <el-form-item label="干基全硫：">
                    <span>{{ props.row.std }}</span>
                  </el-form-item>
                  <el-form-item label="干燥无灰色基挥发分最低：">
                    <span>{{ props.row.ldwgbv }}</span>
                  </el-form-item>
                  <el-form-item label="干燥无灰色基挥发分最高：">
                    <span>{{ props.row.hdwgbv }}</span>
                  </el-form-item>
                  <el-form-item label="粒度（mm）：">
                    <span>{{ props.row.granularity }}</span>
                  </el-form-item>
                  <el-form-item label="灰熔点ST（°C）：">
                    <span>{{ props.row.st }}</span>
                  </el-form-item>
                  <el-form-item label="哈氏可磨系数（HG）：">
                    <span>{{ props.row.hg }}</span>
                  </el-form-item>

                </el-form>
              </template>
            </el-table-column>
            <el-table-column
                label="煤种"
                prop="coaltype">
            </el-table-column>

            <el-table-column
                prop="cpnname"
                label="供货公司">
            </el-table-column>
            <el-table-column
                sortable
                prop="price"
                label="单价"
            >
            </el-table-column>
            <el-table-column
                prop="deliveryplace"
                label="交货地点">
            </el-table-column>
            <el-table-column
                prop="starttime"
                label="时间"
                sortable
            >
            </el-table-column>
          </el-table>
          <div style="width: 10%;"></div>
        </div>

      </div>



      <div style="width: 10%"/>
    </div>



    <div class="footer">
      <Footer/>
    </div>
  </div>
</template>

<script>
import Usersheader from "../index/UsersHeader";
import Header from "../../components/Header";
import Footer from "@/components/Footer";
import Cookies from 'js-cookie'
import {postRequest} from "@/utils/api";
import {postKeyValueRequest} from "@/utils/api";
import {getRequest} from "../../utils/api";
export default {
  name: "Records",
  components: {Footer, Usersheader,Header},
  data() {
    return {
      records: [
        {}
      ]
    }
  },
  methods:{

  },
  mounted() {
    console.log("用户id:"+Cookies.get('userId'));
    postKeyValueRequest("/list/getListByUser",{
      user:(Number)(Cookies.get('userId'))
    }).then((resp) => {
      console.log(resp);
      if (resp) {
        console.log(resp.obj);
        this.records = resp.obj;
      }
    });

    // getRequest("/list/all",{
    // }).then((resp) => {
    //   console.log(resp);
    //   if (resp) {
    //     console.log(resp.obj);
    //     this.records = resp.obj;
    //     console.log(typeof this.records);
    //   }
    // });
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
