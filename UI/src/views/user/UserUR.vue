<template>
  <div>
    <div>
      <div style="display: flex; justify-content: space-between">
        <div>
          <el-button
            type="danger"
            icon="el-icon-delete"
            style="height: 40px; margin-top: 5px"
            @click="deleteUserRoles()"
          >
            批量删除
          </el-button>
          <el-input
            placeholder="请输入员工名进行搜索，可以直接回车搜索..."
            prefix-icon="el-icon-search"
            clearable
            @clear="initUserRoles"
            style="width: 350px; margin-right: 10px; margin-left: 10px"
            v-model="keyword"
            @keydown.enter.native="initUserRoles"
          ></el-input>
          <el-button
            icon="el-icon-search"
            type="primary"
            @click="initUserRoles"
          >
            搜索
          </el-button>
        </div>
      </div>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data="userroles"
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
        <el-table-column prop="role" width="180" align="left" label="角色">
        </el-table-column>
        <el-table-column width="200" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="showEditUserRoleView(scope.row)"
              style="padding: 5px"
              size="big"
              type="info"
              >编辑</el-button
            >
            <el-button
              @click="deleteUserRole(scope.row)"
              style="padding: 5px"
              size="big"
              type="danger"
              >删除角色
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
        <el-form :model="userrole" :rules="rules" ref="userForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="姓名:" prop="name">
                <el-input
                  size="mini"
                  style="width: 150px"
                  prefix-icon="el-icon-edit"
                  v-model="userrole.name"
                  :disabled="true"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="角色:" prop="role">
                <el-radio-group v-model="userrole.role">
                  <el-radio label="系统管理员">系统管理员</el-radio>
                  <el-radio label="普通用户">普通用户</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUserRole">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserUR",
  data() {
    return {
      selectlistRow: [],
      deleteIds: [],
      title: "",
      userroles: [],
      loading: false,
      dialogVisible: false,
      total: 0,
      page: 1,
      keyword: "",
      size: 10,
      userrole: {
        name: "javaboy",
        role: "",
      },

      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        role: [{ required: true, message: "请选择角色", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.initUserRoles();
  },
  methods: {
    handleSelectionChange(val) {
      this.selectlistRow = val;
      return this.selectlistRow;
    },
    showEditUserRoleView(data) {
      this.title = "改变角色";
      this.userrole = data;
      this.dialogVisible = true;
    },
    deleteUserRole(data) {
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
          this.deleteRequest("/user/userrole/" + data.id).then((resp) => {
            if (resp) {
              this.initUserRoles();
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
    deleteUserRoles() {
      console.log(this.selectlistRow);
      this.$confirm("此操作将永久删除,是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          for (let i = 0; i < this.selectlistRow.length; i++) {
            this.deleteRequest("/user/userrole/" + this.selectlistRow[i].id);
            // this.deleteIds.push(this.selectlistRow[i].id);
          }
          this.initUserRoles();
          // console.log(this.deleteIds);
          // this.deleteRequest("/user/basic/" + deleteIds).then((resp) => {
          //   if (resp) {
          //     this.initUserRoles();
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
    updateUserRole() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          this.putRequest("/user/userrole/", this.userrole).then((resp) => {
            if (resp) {
              this.dialogVisible = false;
              this.initUserRoles();
            }
          });
        }
      });
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initUserRoles();
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initUserRoles("advanced");
    },
    initUserRoles() {
      this.loading = true;
      let url = "/user/userrole/?page=" + this.page + "&size=" + this.size;
      url += "&name=" + this.keyword;
      this.getRequest(url).then((resp) => {
        this.loading = false;
        if (resp) {
          this.userroles = resp.data;
          this.total = resp.total;
        }
      });
    },
  },
};
</script>

<style>
</style>
