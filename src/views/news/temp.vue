<template>
  <div class="container">
    <h1 class="title">通知公告</h1>
    <div class="divider"></div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="date"
          v-model="tableData.date"
          label="日期"
          width="150">
      </el-table-column>
      <el-table-column
          prop="province"
          v-model="tableData.province"
          label="省份"
          width="120">
      </el-table-column>
      <el-table-column
          prop="city"
          v-model="tableData.city"
          label="市区"
          width="120">
      </el-table-column>
      <el-table-column
          prop="name"
          v-model="tableData.name"
          label="标题"
          width="300">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--    <ul class="notice-list">-->
    <!--      <li v-for="notice in displayedNotices" :key="notice.id">-->
    <!--        <div class="notice-item">-->
    <!--          <i class="el-icon-s-comment"></i>-->
    <!--          <div class="notice-content">-->
    <!--            <a :href="`/news/${notice.id}`" class="notice-title">{{ notice.title }}</a>-->
    <!--            <span class="date">{{ notice.date }}</span>-->
    <!--          </div>-->
    <!--        </div>-->
    <!--      </li>-->
    <!--    </ul>-->
    <!--    <div class="block">-->
    <!--      <el-pagination-->
    <!--          @current-change="handleCurrentChange"-->
    <!--          :current-page.sync="currentPage"-->
    <!--          :page-size="pageSize"-->
    <!--          layout="prev, pager, next, jumper"-->
    <!--          :total="notices.length"-->
    <!--          background:true-->
    <!--      >-->
    <!--      </el-pagination>-->
    <!--    </div>-->
  </div>
</template>

<script>
export default {
  methods: {
    // handleSizeChange(val) {
    //   console.log(`每页 ${val} 条`);
    // },
    // handleCurrentChange(currentPage) {
    //   console.log(`当前页: ${currentPage}`);
    //   this.currentPage = currentPage;
    // }
    handleClick(row) {
      console.log(row);
    }
  },
  data() {
    return {
      tableData: [
        {
          date:'2023-11-21',
          province:'天津市',
          city:'天津市',
          name:'如何进行垃圾分类',
          link:'https://www.baidu.com'
        }
      ],
      currentPage:1,
      pageSize: 10
    };
  },
  computed: {
    // displayedNotices() {
    //   const startIndex = (this.currentPage - 1) * this.pageSize;
    //   const endIndex = startIndex + this.pageSize;
    //   return this.notices.slice(startIndex, endIndex);
    // }
  }
};
</script>

<style>
.container {
  margin: 20px;
}

.title {
  font-size: 24px;
  margin-bottom: 10px;
  text-align: left;
  color: #819d6a;
}

.el-icon-s-comment {
  color: #819d6a;
  margin-right: 10px;
}

.divider {
  width: 100%;
  height: 1px;
  background: #ccc;
  margin-bottom: 20px;
}

.notice-list {
  list-style: none;
  padding: 0;
}

.notice-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.notice-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.notice-title {
  margin-right: 10px;
  color: #333;
  text-decoration: none;
}

.date {
  color: #999;
}

</style>
