<template>
  <div>
    <!--    导航栏-->
    <gloabalheader/>
    <!--    主体-->

    <!--    副导航栏-->
    <div style="display: flex">
      <div style="width: 10%;"></div>
      <div style="flex: 1">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            text-color="black"
        >
          <el-menu-item index="1">供应大厅</el-menu-item>
          <el-menu-item index="0">求购大厅</el-menu-item>
        </el-menu>
      </div>
      <div style="width: 10%;"></div>
    </div>
    <div class="line"></div>

    <!--    筛选表单-->
    <div id="mainpage" style="display: flex">
      <div style="width: 10%;"></div>
      <div style="flex:1">
        <el-form :model="form" label-width="80px">
          <el-form-item label="煤炭种类">
            <el-radio-group v-model="form.coaltype">
              <el-radio v-for="(item,i) in select.coaltype" :key="i" :label="i===0?'':i" @change="handleQuery(false)">
                {{ item }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="热值">
            <el-radio-group v-model="form.qnetar">
              <el-radio v-for="(item,i) in select.qnetar" :key="i" :label="i===0?'':i" @change="handleQuery(false)">
                {{ item }}
              </el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="全水分">
            <el-radio-group v-model="form.m">
              <el-radio v-for="(item,i) in select.m" :key="i" :label="i===0?'':i" @change="handleQuery(false)">{{
                  item
                }}
              </el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="全硫分">
            <el-radio-group v-model="form.stad">
              <el-radio v-for="(item,i) in select.stad" :key="i" :label="i===0?'':i" @change="handleQuery(false)">{{
                  item
                }}
              </el-radio>
            </el-radio-group>
          </el-form-item>

        </el-form>
      </div>
      <div style="width: 10%;"></div>
    </div>

    <!--    表格-->

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
              <el-form-item label="申请时间：">
                <span>{{ props.row.starttime }}</span>
              </el-form-item>
              <el-button @click="handleClick(props.row)" type="danger" size="small">购买</el-button>
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
            prop="qnetar"
            label="热值">
        </el-table-column>
        <el-table-column
            label="全水分"
            prop="m">
        </el-table-column>
        <el-table-column
            label="全硫分"
            prop="stad">
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

    <!--    分页-->
    <div style="display: flex">
      <div style="width: 10%;"></div>
      <div>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="current"
            :page-sizes="[5,10,20,30,50]"
            :page-size="size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
      <div style="width: 10%;"></div>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Gloabalheader from "@/views/index/Globalheader";
import {postRequest} from "@/utils/api";
import Cookies from 'js-cookie';

export default {
  name: "Sell",
  components: {Gloabalheader, Footer},
  data() {
    return {
      activeIndex: '1',
      select: {
        coaltype: ['全部', '新型煤', '动力煤', '无烟煤', '低质煤', '炼焦媒', '水煤浆', '焦煤', '煤泥'],
        qnetar: ['全部', '1500-3500', '3500-4500', '4500-5000', '5000以上'],
        m: ['全部', '0-10', '10-20', '20以上'],
        stad: ['全部', '0-10', '10-20', '20-30', '30以上']
      },
      form: {
        coaltype: '',
        qnetar: '',
        m: '',
        stad: ''
      },
      records: [
        {}
      ],
      current: 1,//当前页
      size: 5,//每页条数
      total: 0,//总共多少页
      identity: 1//刚开始加载的是供应大厅，也就是卖家发布的单子
    }
  },
  methods: {
    handleQuery(isPage) {
      var Max = 9999999;
      var index1 = this.form.coaltype === '' ? 0 : this.form.coaltype;
      var index2 = this.form.qnetar === '' ? 0 : this.form.qnetar;
      var index3 = this.form.m === '' ? 0 : this.form.m;
      var index4 = this.form.stad === '' ? 0 : this.form.stad;
      var min1 = [0, 1500, 3500, 4500, 5000];
      var min2 = [0, 0, 10, 20];
      var min3 = [0, 0, 10, 20, 30];
      var max1 = [Max, 3500, 4500, 5000, Max];
      var max2 = [Max, 10, 20, Max];
      var max3 = [Max, 10, 20, 30, Max];
      var obj = {
        coaltype: this.select.coaltype[index1],
        qmin: min1[index2],
        qmax: max1[index2],
        mmin: min2[index3],
        mmax: max2[index3],
        smin: min3[index4],
        smax: max3[index4],
        isPage: isPage,
        current: this.current,
        size: this.size,
        identity: this.identity
      };
      console.log(obj);
      postRequest("/list/select", obj).then((resp) => {
        if (resp) {
          console.log(resp);
          this.records = resp.obj.records;
          this.total = resp.obj.total;
        } else {
          alert("搜索失败");
        }
      });
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      console.log(typeof (key));
      this.identity = Number(key);
      console.log(this.identity);
      this.handleQuery(false)
    },
    handleClick(info) {
      // alert("1");
      console.log(info);
      var no = info.no;//订单编号
      var id = (Number)(Cookies.get('userId'));//买家id
      console.log("买家："+id);
      postRequest("/list/buy", {
        no: no,
        id: id
      }).then((resp) => {
        if (resp) {
          console.log("buy successful");
        }
      });
    },

    //用于展示具体信息，详情
    handleEdit(row) {
      alert(row);
      console.log(row)
    },

    //每页展示
    handleSizeChange(val) {
      this.size = val;
      this.handleQuery(true);
    },

    //分页跳转
    handleCurrentChange(val) {
      console.log('**********');
      console.log(val);
      console.log('**********');
      this.current = val;
      this.handleQuery(true);
    },

    load() {
      postRequest("/list/page", {
        current: this.current,
        size: this.size,
        identity: this.identity //当前页默认是0
      }).then((resp) => {
        if (resp) {
          // alert("success");
          console.log(resp.obj);
          this.records = resp.obj.records;
          this.total = resp.obj.total;
          //有了total就行
          // this.total=resp.obj.total;
        }
      });
    }
  },
  //页面渲染之前，获取用户信息
  // beforeCreate() {
  // this.getRequest("/list/all").then((resp) => {
  //   if (resp) {
  //     // alert("success");
  //     console.log(resp.obj);
  //     this.records = resp.obj;
  //   }
  // });

  // }
  created() {
    this.load();
  }
}
</script>

<style scoped>
.el-menu-item.is-active {
  background-color: rgb(231, 235, 240) !important;
}

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
