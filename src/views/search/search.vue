<template>
  <div>
    <el-form :inline="true" :model="searchData" class="demo-form-inline">
      <el-form-item label="垃圾类别">
        <el-input v-model="searchData.class" placeholder="请输入垃圾类别"></el-input>
      </el-form-item>
      <el-form-item label="垃圾小类">
        <el-input v-model="searchData.min_class" placeholder="请输入垃圾小类"></el-input>
      </el-form-item>
      <el-form-item label="具体垃圾">
        <el-input v-model="searchData.garbage" placeholder="请输入具体垃圾"></el-input>
      </el-form-item>
      <el-form-item label="处理方式">
        <el-input v-model="searchData.way" placeholder="请输入垃圾处理方式"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" style="background-color: #819d6a">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="reset" style="background-color: #819d6a">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20" class="card-row">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold;color: #819d6a;font-size: large">垃圾分类图谱</span>
          </div>
          <div>
            知识图谱
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="search-card">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold;color: #819d6a;font-size: large">垃圾详情</span>
          </div>
          <el-table
              :data="filterData"
              height="445"
              border
          >
            <el-table-column
                prop="class"
                label="垃圾类别"
                align="center"
                width="150">
            </el-table-column>
            <el-table-column
                prop="min_class"
                label="垃圾小类"
                align="center"
                width="150">
            </el-table-column>
            <el-table-column
                prop="garbage"
                width="150"
                label="具体垃圾"
                align="center">
            </el-table-column>
            <el-table-column
                prop="way"
                label="处理方式"
                width="150"
                align="center">
            </el-table-column>
          </el-table>
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[8, 20, 30, 40]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "search",
  data() {
    return {
      searchData: {
        class:'',
        min_class:'',
        garbage:'',
        way:'',
      },
      tableData: [{
        class: '可回收垃圾',
        min_class: '废纸类',
        garbage: '草稿纸',
        way:'回收'
      }, {
        class: '可回收垃圾',
        min_class: '金属类',
        garbage: '铁勺',
        way:'回收'
      }, {
        class: '有害垃圾',
        min_class: '废水银类',
        garbage: '体温计',
        way:'专业机构'
      }, {
        class: '湿垃圾',
        min_class: '剩菜剩饭',
        garbage: '过期的食物',
        way:'堆肥'
      }, {
        class: '大件垃圾',
        min_class: '废家具类',
        garbage: '沙发',
        way:'回收'
      }, {
        class: '可回收垃圾',
        min_class: '玻璃类',
        garbage: '水杯',
        way:'焚烧'
      }, {
        class: '湿垃圾',
        min_class: '植物残枝',
        garbage: '花草',
        way:'堆肥'
      }],
      filterData:[],
      currentPage: 1, // 当前页数
      pageSize: 8, // 每页显示条数
      total: '', // 总条数（假设为100条）
    }
  },
  mounted() {
    this.filterData = this.tableData;
    this.total = this.tableData.length;
  },
  methods: {
    onSubmit() {
      const { class: searchClass, min_class: searchMinClass, garbage: searchGarbage, way: searchWay } = this.searchData;
      if (searchClass === '' && searchMinClass === '' && searchGarbage === '' && searchWay === '') {
        this.filterData = this.tableData; // 当所有查询条件为空时，展示所有数据
      } else {
        this.filterData = this.tableData.filter(item => {
          return (
              (searchClass === '' || item.class.includes(searchClass)) &&
              (searchMinClass === '' || item.min_class.includes(searchMinClass)) &&
              (searchGarbage === '' || item.garbage.includes(searchGarbage)) &&
              (searchWay === '' || item.way.includes(searchWay))
          );
        });
      }
    },
    reset(){
      this.searchData.garbage = '';
      this.searchData.class = '';
      this.searchData.min_class = '';
      this.searchData.way = '';
      this.filterData = this.tableData;
    },
    // 处理页码改变
    handleCurrentChange(val) {
      this.currentPage = val;
      // 根据当前页数请求对应数据
      // 例如：this.getTableData();
    },
    // 处理每页条数改变
    handleSizeChange(val) {
      this.pageSize = val;
      // 根据每页条数请求对应数据
      // 例如：this.getTableData();
    }
  }
}
</script>

<style scoped>
/*.demo-form-inline{*/
/*  background-color: #e5e5e5;*/
/*}*/
.el-form-item{
  margin-top: 18px;
}
.box-card{
  width: 100%;
  height:690px;
  margin-top: 10px;
  margin-left: 10px;
}
.search-card{
  width: 100%;
  height:100%;
  margin-top: 10px;
  margin-left: 10px;
}
</style>