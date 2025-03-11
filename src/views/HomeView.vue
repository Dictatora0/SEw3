<template>
  <div style="padding:10px">
<!--    功能区域-->
    <div style="margin:10px 0">
      <el-button type="primary">新增</el-button>
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
      <el-table-column prop="date" label="Date"
      sortable/>
      <el-table-column prop="name" label="Name" />
      <el-table-column prop="address" label="Address" />
      <el-table-column fixed="right" label="Operations" min-width="120">
        <template #default>
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
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";

// 搜索框
const search = ref("");

// 当前页码 & 每页数量
const currentPage = ref(1);
const pageSize = ref(2);

// 原始数据
const tableData = ref([
  { date: "2023-03-01", name: "Alice", address: "北京" },
  { date: "2023-03-02", name: "Bob", address: "上海" },
  { date: "2023-03-03", name: "Charlie", address: "广州" },
  { date: "2023-03-04", name: "David", address: "深圳" },
  { date: "2023-03-05", name: "Eve", address: "杭州" },
  { date: "2023-03-06", name: "Frank", address: "南京" },
]);

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
</script>