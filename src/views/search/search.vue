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
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold;color: #819d6a;font-size: large">垃圾分类图谱</span>
          </div>
          <div>
            知识图谱
            <div ref="graphContainer" style="overflow: hidden; position: relative; height: 400px;"></div>
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
                prop="classname"
                label="垃圾类别"
                align="center"
                width="150">
            </el-table-column>
            <el-table-column
                prop="minClass"
                label="垃圾小类"
                align="center"
                width="150">
            </el-table-column>
            <el-table-column
                prop="name"
                width="150"
                label="具体垃圾"
                align="center">
            </el-table-column>
            <el-table-column
                prop="handle"
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
          min_lass:'',
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
    },
    drawGraph() {
      // 获取图谱数据
      this.graphData = require('../../assets/records.json');

      // 创建 SVG 容器
      const width = 800;
      const height = 400;
      const svg = d3.select(this.$refs.graphContainer)
          .append('svg')
          .attr('width', width)
          .attr('height', height)
          .attr('viewBox', `0 0 ${width} ${height}`)
          .attr('preserveAspectRatio', 'xMidYMid meet');


      // 定义箭头
      svg.append('defs').append('marker')
          .attr('id', 'arrow')
          .attr('viewBox', '0 -5 10 10')
          .attr('refX', 20)
          .attr('refY', 0)
          .attr('markerWidth', 6)
          .attr('markerHeight', 6)
          .attr('orient', 'auto')
          .append('path')
          .attr('d', 'M0,-5L10,0L0,5')
          .attr('fill', '#999');

      // 绘制关系
      const links = svg.selectAll('line')
          .data(this.graphData)
          .enter().append('line')
          .attr('marker-end', 'url(#arrow)')
          .style('stroke', '#999')
          .style('stroke-width', 2);

      // 绘制实体
      const nodes = svg.selectAll('circle')
          .data(this.graphData)
          .enter().append('circle')
          .attr('r', 30)
          .style('fill', d => {
            // 根据不同的实体设置不同的颜色
            if (d.n.properties.class === '可回收垃圾') return 'green';
            if (d.n.properties.class === '有害垃圾') return 'red';
            if (d.n.properties.class === '湿垃圾（厨余垃圾）') return 'blue';
            if (d.n.properties.class === '干垃圾') return 'yellow';
            if (d.n.properties.class === '大件垃圾') return 'purple';
          })
          .on('click', d => {
            // 点击实体获取信息
            console.log(d.n.properties);
          });

      // 定义力导向图模拟器
      const simulation = d3.forceSimulation(this.graphData)
          .force('link', d3.forceLink().id(d => d.n.identity))
          .force('charge', d3.forceManyBody())
          .force('center', d3.forceCenter(400, 300));

      // 更新节点和关系的位置
      simulation
          .nodes(this.graphData)
          .on('tick', () => {
            links
                .attr('x1', d => d.source.x)
                .attr('y1', d => d.source.y)
                .attr('x2', d => d.target.x)
                .attr('y2', d => d.target.y);

            nodes
                .attr('cx', d => d.x)
                .attr('cy', d => d.y);
          });

      simulation.force('link')
          .links(this.graphData);
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