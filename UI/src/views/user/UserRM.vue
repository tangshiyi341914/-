<template>
  <div>
    <div>
      <div style="display: flex; justify-content: space-between">
        <div>
          <el-button
            type="danger"
            icon="el-icon-delete"
            style="height: 40px; margin-top: 5px"
            @click="deleteMenuRoles()"
          >
            批量删除
          </el-button>
          <el-input
            placeholder="请输入菜单名进行搜索，可以直接回车搜索..."
            prefix-icon="el-icon-search"
            clearable
            @clear="initMenuRoles"
            style="width: 350px; margin-right: 10px; margin-left: 10px"
            v-model="keyword"
            @keydown.enter.native="initMenuRoles"
          ></el-input>
          <el-button
            icon="el-icon-search"
            type="primary"
            @click="initMenuRoles"
          >
            搜索
          </el-button>
        </div>
      </div>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data="menuroles"
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
          label="菜单名"
          width="180"
        >
        </el-table-column>
        <el-table-column prop="roles" width="180" align="left" label="角色">
        </el-table-column>
        <el-table-column width="200" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="showEditMenuRoleView(scope.row)"
              style="padding: 5px"
              size="big"
              type="info"
              >编辑</el-button
            >
            <el-button
              @click="deleteMenuRole(scope.row)"
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
        <el-form :model="menurole" :rules="rules" ref="userForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="菜单名:" prop="name">
                <el-input
                  size="mini"
                  style="width: 150px"
                  prefix-icon="el-icon-edit"
                  v-model="menurole.name"
                  :disabled="true"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="角色:" prop="roles">
                <el-radio-group v-model="menurole.roles">
                  <el-radio :label="role1">系统管理员</el-radio>
                  <el-radio :label="role2">普通用户</el-radio>
                  <el-radio :label="role3">系统管理员普通用户</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateMenuRole">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserUR",
  data() {
    let role1 = ["系统管理员"];
    let role2 = ["系统管理员"];
    let role3 = ["系统管理员", "普通用户"];
    return {
      role1: role1,
      role2: role2,
      role3: role3,
      selectlistRow: [],
      deleteIds: [],
      title: "",
      menuroles: [],
      loading: false,
      dialogVisible: false,
      total: 0,
      page: 1,
      keyword: "",
      size: 10,
      menurole: {
        name: "javaboy",
        roles: [],
      },

      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        roles: [{ required: true, message: "请选择角色", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.initMenuRoles();
  },
  methods: {
    handleSelectionChange(val) {
      this.selectlistRow = val;
      return this.selectlistRow;
    },
    showEditMenuRoleView(data) {
      this.title = "改变角色";
      this.menurole = data;
      this.dialogVisible = true;
    },
    deleteMenuRole(data) {
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.deleteRequest("/user/menurole/" + data.id).then((resp) => {
            if (resp) {
              this.initMenuRoles();
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
    deleteMenuRoles() {
      console.log(this.selectlistRow);
      this.$confirm("此操作将永久删除,是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          for (let i = 0; i < this.selectlistRow.length; i++) {
            this.deleteRequest("/user/menurole/" + this.selectlistRow[i].id);
            // this.deleteIds.push(this.selectlistRow[i].id);
          }
          this.initMenuRoles();
          // console.log(this.deleteIds);
          // this.deleteRequest("/user/basic/" + deleteIds).then((resp) => {
          //   if (resp) {
          //     this.initMenuRoles();
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
    updateMenuRole() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          this.putRequest("/user/menurole/", this.menurole).then((resp) => {
            console.log(this.menurole);
            if (resp) {
              this.dialogVisible = false;
              this.initMenuRoles();
            }
          });
        }
      });
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initMenuRoles();
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initMenuRoles();
    },
    initMenuRoles() {
      this.loading = true;
      let url = "/user/menurole/?page=" + this.page + "&size=" + this.size;
      url += "&name=" + this.keyword;
      this.getRequest(url).then((resp) => {
        this.loading = false;
        if (resp) {
          this.menuroles = resp.data;
          // menuroles.foreach((menurole) => {
          //   menurole.roles = menurole.roles.toString();
          // });
          this.total = resp.total;
        }
      });
    },
  },
};
</script>
<style>
</style>
