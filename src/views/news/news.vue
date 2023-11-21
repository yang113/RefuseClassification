<template>
  <div class="container">
    <h1 class="title">通知公告</h1>
    <div class="divider"></div>
    <el-table
        :data="filteredTableData"
        style="width: 100%">
      <el-table-column
          label="日期"
          prop="date"
          sortable
          :sort-orders="['descending', 'ascending']">
      </el-table-column>
      <el-table-column
          label="省份"
          prop="province">
      </el-table-column>
      <el-table-column
          label="城市"
          prop="city">
      </el-table-column>
      <el-table-column
          label="标题"
          prop="name">
      </el-table-column>
      <el-table-column
          align="right">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="mini"
              placeholder="按城市搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
              size="mini"
              style="background-color: #a3d48e"
              @click="handleEdit(scope.$index, scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[8, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
    </el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
      let src = row.link;
      window.open(src, '_blank');
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
  },
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类呢？',
        link:'https://www.baidu.com'
      }, {
        date: '2016-05-04',
        province:'上海市',
        city:'普陀区',
        name: '垃圾分类新条款',
        link:'https://www.baidu.com'
      }, {
        date: '2016-05-06',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      }, {
        date: '2016-05-08',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      },{
        date: '2016-05-08',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      },{
        date: '2016-05-08',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      },{
        date: '2016-05-08',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      },{
        date: '2016-05-08',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      },{
        date: '2016-05-08',
        province:'上海市',
        city:'普陀区',
        name: '如何进行垃圾分类',
        link:'https://www.baidu.com'
      }],
      search: '',
      currentPage: 1,
      pageSize: 8
    };
  },
  computed: {
    filteredTableData() {
      const filteredData = this.tableData.filter(data => {
        return !this.search || data.city.toLowerCase().includes(this.search.toLowerCase());
      });
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return filteredData.slice(startIndex, endIndex);
    }
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

.divider {
  width: 100%;
  height: 1px;
  background: #ccc;
  margin-bottom: 20px;
}
</style>
