<template>
  <div class="container">
    <el-table
        :data="tableData"
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
          label="标题">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.title" placement="top">
            <div class="title-cell">{{ scope.row.title }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column
          align="right">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="small"
              placeholder="按城市搜索"
              @input="handleSearch"
          />
        </template>
        <template slot-scope="scope">
          <el-button
              size="mini"
              style="background-color: #a3d48e;color: #fff"
              @click="handleEdit(scope.$index, scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        class="pageCut"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
    </el-pagination>
  </div>
</template>

<script>
import request from "@/utils/request";

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
    load(){
      request.get("information/getinfo",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          city:this.search
        }
      }).then(res=>{
        console.log('load',res)
        res.records.forEach(item=>{
          item.date = item.date.toString().split('T')[0];
        })
        this.tableData = res.records;
      })
    },
    handleSearch() {
      console.log('hhh')
      const filteredData = this.tableData.filter(data => {
        return !this.search || data.city.toLowerCase().includes(this.search.toLowerCase());
      });
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return filteredData.slice(startIndex, endIndex);
    }
  },
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 10
    };
  },
  mounted() {
    this.load();
  },
  computed: {
    filteredTableData() {
      console.log('hhh')
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
.el-table__header th{
  color: #819d6a; /* 设置表格首行字体颜色 */
  font-size: 16px;
}

.el-button {
  transition: color 0.3s; /* 设置按钮字体颜色过渡效果 */
}

.el-button:hover {
  color: #4d5942; /* 设置按钮鼠标悬浮时的字体颜色 */
}

.el-table__body td .cell {
  max-width: 200px; /* 设置标题一列的固定宽度 */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* 当文本内容超过最大宽度时，用省略号展示 */
}
.el-pagination .el-pager li:not(.disabled):hover {
  color: #819d6a; /* 设置分页器鼠标悬浮时的颜色 */
}

.el-pagination .el-pager li.active {
  color: #819d6a; /* 设置分页器被选中页的字体颜色 */
}

.el-input__inner:focus {
  border-color: #819d6a; /* 设置搜索框被点击时的边框颜色 */
}

.el-pagination .btn-next:hover,
.el-pagination .btn-prev:hover {
  color: #819d6a; /* 设置分页器左右按键鼠标悬浮时的颜色 */
}
.el-pagination .el-select .el-input .el-input__inner:hover{
  border-color: #819d6a;
}
.pageCut{
  margin-top: 10px;
}
.el-button:hover{
  border-color: #819d6a;
}
.title-cell {
  max-width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
