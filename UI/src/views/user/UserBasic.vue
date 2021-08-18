<template>
  <div>
    <div>
      <div style="display: flex; justify-content: space-between">
        <div>
          <el-button
            type="danger"
            icon="el-icon-delete"
            style="height: 40px; margin-top: 5px"
            @click="deleteUsers()"
          >
            批量删除
          </el-button>
          <el-input
            placeholder="请输入员工名进行搜索，可以直接回车搜索..."
            prefix-icon="el-icon-search"
            clearable
            @clear="initUsers"
            style="width: 350px; margin-right: 10px; margin-left: 10px"
            v-model="keyword"
            @keydown.enter.native="initUsers"
            :disabled="showAdvanceSearchView"
          ></el-input>
          <el-button
            icon="el-icon-search"
            type="primary"
            @click="initUsers"
            :disabled="showAdvanceSearchView"
          >
            搜索
          </el-button>
          <el-button
            type="primary"
            @click="showAdvanceSearchView = !showAdvanceSearchView"
          >
            <i
              :class="
                showAdvanceSearchView
                  ? 'el-icon-arrow-up'
                  : 'el-icon-arrow-down'
              "
              aria-hidden="true"
            ></i>
            高级搜索
          </el-button>
        </div>
        <div>
          <el-upload
            :show-file-list="false"
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            :on-error="onError"
            :disabled="importDataDisabled"
            style="display: inline-flex; margin-right: 8px"
            action="/api/user/basic/import"
          >
            <el-button
              :disabled="importDataDisabled"
              type="success"
              :icon="importDataBtnIcon"
            >
              {{ importDataBtnText }}
            </el-button>
          </el-upload>
          <el-button type="success" @click="exportData" icon="el-icon-download">
            导出数据
          </el-button>
          <el-button
            type="primary"
            icon="el-icon-plus"
            @click="showAddUserView"
          >
            添加用户
          </el-button>
        </div>
      </div>
      <transition name="slide-fade">
        <div
          v-show="showAdvanceSearchView"
          style="
            border: 1px solid #409eff;
            border-radius: 5px;
            box-sizing: border-box;
            padding: 5px;
            margin: 10px 0px;
          "
        >
          <el-row>
            <el-col :span="5">
              联系地址:
              <el-input
                v-model="searchValue.address"
                placeholder="联系地址"
                size="mini"
                style="width: 130px"
              >
              </el-input>
            </el-col>
            <el-col :span="4">
              电话:
              <el-input
                v-model="searchValue.phone"
                placeholder="电话号码"
                size="mini"
                style="width: 130px"
              >
              </el-input>
            </el-col>
            <el-col :span="4">
              邮箱:
              <el-input
                v-model="searchValue.email"
                placeholder="邮箱号码"
                size="mini"
                style="width: 130px"
              >
              </el-input> </el-col
            ><el-col :span="10">
              出生日期:
              <el-date-picker
                v-model="searchValue.beginDateScope"
                type="daterange"
                size="mini"
                unlink-panels
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row style="margin-top: 10px">
            <el-col :span="5" :offset="10">
              <el-button size="mini" @click="changeShowAdvanceSearchView()"
                >取消</el-button
              >
              <el-button
                size="mini"
                icon="el-icon-search"
                type="primary"
                @click="initUsers('advanced')"
                >搜索</el-button
              >
            </el-col>
          </el-row>
        </div>
      </transition>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data="users"
        stripe
        border
        v-loading="loading"
        element-loading-text="正在加载..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column
          prop="name"
          fixed
          align="left"
          label="姓名"
          width="100"
        >
        </el-table-column>
        <el-table-column prop="gender" label="性别" align="left" width="70">
        </el-table-column>
        <el-table-column
          prop="birthday"
          width="110"
          align="left"
          label="出生日期"
        >
        </el-table-column>
        <el-table-column prop="email" width="180" align="left" label="电子邮件">
        </el-table-column>
        <el-table-column prop="phone" width="180" align="left" label="电话号码">
        </el-table-column>
        <el-table-column
          prop="address"
          width="180"
          align="left"
          label="联系地址"
        >
        </el-table-column>
        <el-table-column
          prop="cpnName"
          width="180"
          align="left"
          label="代表公司"
        >
        </el-table-column>
        <el-table-column fixed="right" width="200" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="showEditUserView(scope.row)"
              style="padding: 5px"
              size="big"
              type="info"
              >编辑</el-button
            >
            <el-button
              @click="deleteUser(scope.row)"
              style="padding: 5px"
              size="big"
              type="danger"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex; justify-content: flex-end">
        <el-pagination
          background
          @current-change="currentChange"
          @size-change="sizeChange"
          layout="sizes, prev, pager, next, jumper, ->, total, slot"
          :total="total"
        >
        </el-pagination>
      </div>
    </div>
    <el-dialog :title="title" :visible.sync="dialogVisible" width="80%">
      <div>
        <el-form :model="user" :rules="rules" ref="userForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="姓名:" prop="name">
                <el-input
                  size="mini"
                  style="width: 150px"
                  prefix-icon="el-icon-edit"
                  v-model="user.name"
                  placeholder="请输入用户姓名"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="性别:" prop="gender">
                <el-radio-group v-model="user.gender">
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="出生日期:" prop="birthday">
                <el-date-picker
                  v-model="user.birthday"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width: 150px"
                  placeholder="出生日期"
                >
                </el-date-picker>
              </el-form-item> </el-col
            ><el-col :span="7">
              <el-form-item label="电话号码:" prop="phone">
                <el-input
                  size="mini"
                  style="width: 200px"
                  prefix-icon="el-icon-phone"
                  v-model="user.phone"
                  placeholder="电话号码"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="电子邮箱:" prop="email">
                <el-input
                  size="mini"
                  style="width: 150px"
                  prefix-icon="el-icon-message"
                  v-model="user.email"
                  placeholder="请输入电子邮箱"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="联系地址:" prop="address">
                <el-input
                  size="mini"
                  style="width: 200px"
                  prefix-icon="el-icon-edit"
                  v-model="user.address"
                  placeholder="请输入联系地址"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="状态:" prop="enabled">
                <el-radio-group v-model="user.enabled">
                  <el-radio label="true">启动</el-radio>
                  <el-radio label="false">冻结</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="代表公司:" prop="cpnName">
                <el-input
                  size="mini"
                  style="width: 200px"
                  prefix-icon="el-icon-edit"
                  v-model="user.cpnName"
                  placeholder="请输入联公司名"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="账号:" prop="username">
                <el-input
                  size="mini"
                  style="width: 200px"
                  prefix-icon="el-icon-edit"
                  v-model="user.username"
                  placeholder="请输入账号"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="密码:" prop="password">
                <el-input
                  type="password"
                  size="mini"
                  style="width: 200px"
                  prefix-icon="el-icon-edit"
                  v-model="user.password"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doAddUser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserBasic",
  data() {
    return {
      searchValue: {
        address: null,
        phone: null,
        email: null,
        beginDateScope: null,
      },
      selectlistRow: [],
      deleteIds: [],
      title: "",
      importDataBtnText: "导入数据",
      importDataBtnIcon: "el-icon-upload2",
      importDataDisabled: false,
      users: [],
      loading: false,
      showAdvanceSearchView: false,
      dialogVisible: false,
      total: 0,
      page: 1,
      keyword: "",
      size: 10,
      user: {
        name: "javaboy",
        gender: "男",
        birthday: "1989-12-31",
        enabled: true,
        username: "",
        password: "",
        email: "laowang@qq.com",
        phone: "18565558897",
        address: "深圳市南山区",
        cpnName: "",
      },

      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        gender: [{ required: true, trigger: "blur" }],
        birthday: [
          { required: true, message: "请输入出生日期", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "邮箱格式不正确",
            trigger: "blur",
          },
        ],
        enabled: [{ required: true, trigger: "blur" }],
        phone: [{ required: true, message: "请输入电话号码", trigger: "blur" }],
        address: [
          { required: true, message: "请输入用户地址", trigger: "blur" },
        ],
        cpnName: [{ required: true, message: "请输入公司名", trigger: "blur" }],
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.initUsers();
  },
  methods: {
    changeShowAdvanceSearchView() {
      this.showAdvanceSearchView = false;
    },
    handleSelectionChange(val) {
      this.selectlistRow = val;
      return this.selectlistRow;
    },
    onError(err, file, fileList) {
      this.importDataBtnText = "导入数据";
      this.importDataBtnIcon = "el-icon-upload2";
      this.importDataDisabled = false;
    },
    onSuccess(response, file, fileList) {
      this.importDataBtnText = "导入数据";
      this.importDataBtnIcon = "el-icon-upload2";
      this.importDataDisabled = false;
      this.initUsers();
    },
    beforeUpload() {
      this.importDataBtnText = "正在导入";
      this.importDataBtnIcon = "el-icon-loading";
      this.importDataDisabled = true;
    },
    exportData() {
      window.open("/api/user/basic/export", "_parent");
    },
    emptyUser() {
      this.user = {
        name: "",
        gender: "",
        birthday: "",
        enabled: true,
        email: "",
        phone: "",
        address: "",
        cpnName: "",
      };
    },
    showEditUserView(data) {
      this.title = "编辑用户信息";
      this.user = data;
      this.dialogVisible = true;
    },
    deleteUser(data) {
      this.$confirm(
        "此操作将永久删除【" + data.name + "】, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.deleteRequest("/user/basic/" + data.id).then((resp) => {
            if (resp) {
              this.initUsers();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    deleteUsers() {
      console.log(this.selectlistRow);
      this.$confirm("此操作将永久删除,是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          for (let i = 0; i < this.selectlistRow.length; i++) {
            this.deleteRequest("/user/basic/" + this.selectlistRow[i].id);
            // this.deleteIds.push(this.selectlistRow[i].id);
          }
          this.initUsers();
          // console.log(this.deleteIds);
          // this.deleteRequest("/user/basic/" + deleteIds).then((resp) => {
          //   if (resp) {
          //     this.initUsers();
          //   }
          // });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    doAddUser() {
      if (this.user.id) {
        this.$refs["userForm"].validate((valid) => {
          if (valid) {
            this.putRequest("/user/basic/", this.user).then((resp) => {
              if (resp) {
                this.dialogVisible = false;
                this.initUsers();
              }
            });
          }
        });
      } else {
        this.$refs["userForm"].validate((valid) => {
          if (valid) {
            this.postRequest("/user/basic/", this.user).then((resp) => {
              if (resp) {
                this.dialogVisible = false;
                this.initUsers();
              }
            });
          }
        });
      }
    },

    sizeChange(currentSize) {
      this.size = currentSize;
      this.initUsers();
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initUsers("advanced");
    },

    showAddUserView() {
      this.emptyUser();
      this.title = "添加员工";

      this.dialogVisible = true;
    },
    initUsers(type) {
      this.loading = true;
      let url = "/user/basic/?page=" + this.page + "&size=" + this.size;
      if (type && type == "advanced") {
        if (this.searchValue.address) {
          url += "&address=" + this.searchValue.address;
        }
        if (this.searchValue.phone) {
          url += "&phone=" + this.searchValue.phone;
        }
        if (this.searchValue.email) {
          url += "&email=" + this.searchValue.email;
        }
        if (this.searchValue.beginDateScope) {
          url += "&beginDateScope=" + this.searchValue.beginDateScope;
        }
      } else {
        url += "&name=" + this.keyword;
      }
      this.getRequest(url).then((resp) => {
        this.loading = false;
        if (resp) {
          this.users = resp.data;
          this.total = resp.total;
        }
      });
    },
  },
};
</script>

<style>
/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>
