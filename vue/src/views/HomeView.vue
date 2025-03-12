<template>
  <div style="padding:10px">
<!--    功能区域-->
    <div style="margin:10px 0">
      <el-button type="primary" @click = "add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索区域-->
    <div style="margin:10px 0">
      <el-input v-model="search" style="width: 240px" placeholder="请输入关键字" />
      <el-button type = "primary" style="margin-left: 5px">查询</el-button>

    </div>
    <el-table :data="tableData"
               border
               stripe
               style="width: 100%">
      <el-table-column prop="id" label="ID"
      sortable/>
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />
      <el-table-column fixed="right" label="Operations" min-width="120">
        <template #default="{ row }">
          <el-button link type="primary"  @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-popconfirm title="Are you sure to delete this?">
            <template #reference>
              <el-button type="text">Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin:10px 0">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :size="size"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <el-dialog
          v-model="dialogVisible"
          title="Tips"
          width="500"
          :before-close="handleClose"
      >
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%" />
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%" />
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%" />
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label="未知">未知</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%" />
          </el-form-item>
        </el-form>

        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">
              确定
            </el-button>
          </div>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";

// 表单数据
const form = ref({
  username: ""
});

// 搜索框
const search = ref("");

// 当前页码 & 每页数量
const currentPage = ref(1);
const pageSize = ref(2);

// 原始数据
const tableData = ref([

]);

// 打开弹窗
const add = () => {
  dialogVisible.value = true;
  form.value = { username: "" };
};

const save = () => {
  
};

// 弹窗可见性
const dialogVisible = ref(false);

// 计算符合搜索条件的数据
const filteredData = computed(() => {
  if (!search.value) return tableData.value;
  return tableData.value.filter((item) =>
      item.name.includes(search.value) || item.address.includes(search.value)
  );
});

// 计算总条数
const total = computed(() => filteredData.value.length);

// 计算当前页的数据
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return filteredData.value.slice(start, end);
});

// 切换每页条数
const handleSizeChange = (newSize) => {
  pageSize.value = newSize;
  currentPage.value = 1; // 切换后跳到第一页
};

// 切换页码
const handleCurrentChange = (newPage) => {
  currentPage.value = newPage;
};

// 编辑操作
const handleEdit = (row) => {
  form.value.username = row.username;
  dialogVisible.value = true;
};

// 删除操作
const handleDelete = (row) => {
  tableData.value = tableData.value.filter(item => item.id !== row.id);
};

</script>