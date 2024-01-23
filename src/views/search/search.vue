<template>
  <div>
    <el-form :inline="true" :model="searchData" class="demo-form-inline">
      <el-form-item label="垃圾类别">
        <el-input v-model="searchData.classname" placeholder="请输入垃圾类别"></el-input>
      </el-form-item>
      <el-form-item label="垃圾小类">
        <el-input v-model="searchData.minClass" placeholder="请输入垃圾小类"></el-input>
      </el-form-item>
      <el-form-item label="具体垃圾">
        <el-input v-model="searchData.name" placeholder="请输入具体垃圾"></el-input>
      </el-form-item>
      <el-form-item label="处理方式">
        <el-input v-model="searchData.handle" placeholder="请输入垃圾处理方式"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" style="background-color: #819d6a">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="reset" style="background-color: #819d6a">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20" class="card-row">
      <el-col :span="20" style="width:100%;margin-top: -10px">
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
                prop="classname"
                label="垃圾类别"
                align="center"
                width="340">
            </el-table-column>
            <el-table-column
                prop="minClass"
                label="垃圾小类"
                align="center"
                width="340">
            </el-table-column>
            <el-table-column
                prop="name"
                width="345"
                label="具体垃圾"
                align="center">
            </el-table-column>
            <el-table-column
                prop="handle"
                label="处理方式"
                width="345"
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
import request from "@/utils/request";
import * as d3 from '../../../../node_modules/d3';

export default {
  name: "search",
  data() {
    return {
      searchData: {
        classname:'',
        minClass:'',
        name:'',
        handle:'',
      },
      tableData: [],
      filterData:[],
      currentPage: 1, // 当前页数
      pageSize: 8, // 每页显示条数
      total: '', // 总条数（假设为100条）
      graphData: []
    }
  },
  mounted() {
    this.filterData = this.tableData;
    this.total = this.tableData.length;
    this.load();
    this.drawGraph();
  },
  methods: {
    load(){
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: '',
          min_class:'',
          classname:'',
          handle:''
        }
      }).then(res=>{
        console.log('load',res)
        this.tableData = res.records;
        this.filterData = this.tableData;
        this.total = res.total;
      })
    },
    onSubmit() {
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: this.searchData.name,
          min_class: this.searchData.minClass,
          classname: this.searchData.classname,
          handle: this.searchData.handle
        }
      }).then(res=>{
        console.log('load',res)
        this.filterData = res.records;
        this.total = res.total;
      })
    },
    reset(){
      this.searchData.name = '';
      this.searchData.classname = '';
      this.searchData.minClass = '';
      this.searchData.handle = '';
      this.filterData = this.tableData;
    },
    // 处理页码改变
    handleCurrentChange(val) {
      this.currentPage = val;
      // 根据当前页数请求对应数据
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: this.searchData.name,
          min_class: this.searchData.minClass,
          classname: this.searchData.classname,
          handle: this.searchData.handle
        }
      }).then(res=>{
        console.log('load',res)
        this.filterData = res.records;
        this.total = res.total;
      })
    },
    // 处理每页条数改变
    handleSizeChange(val) {
      this.pageSize = val;
      // 根据每页条数请求对应数据
      request.get("classification/getclass",{
        params:{
          pageNum:this.currentPage,
          pageSize: this.pageSize,
          name: this.searchData.name,
          min_class: this.searchData.minClass,
          classname: this.searchData.classname,
          handle: this.searchData.handle
        }
      }).then(res=>{
        console.log('load',res)
        this.filterData = res.records;
        this.total = res.total;
      })
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