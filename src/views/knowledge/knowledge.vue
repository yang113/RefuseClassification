<template>
  <div class="all-show">
    <el-row :gutter="20">
      <el-col :span="6" v-for="(item, index) in showData"
              :key="index" :style="{backgroundColor: item.color}"
              class="data-show">
        <div class="data-show-detail">
          <el-statistic
              group-separator=","
              :value="item.value"
              :title="item.name"
          ></el-statistic>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20" class="card-row">
      <el-col :span="14">
        <el-card class="box-card" style="height: 550px">
          <div slot="header" class="clearfix">
              <button @click="loadGraphData1()" class="filter_button">可回收垃圾</button>
              <button @click="loadGraphData2()" class="filter_button">有害垃圾</button>
              <button @click="loadGraphData3()" class="filter_button">干垃圾</button>
              <button @click="loadGraphData4()" class="filter_button">湿垃圾</button>
              <button @click="loadGraphData5()" class="filter_button">大件垃圾</button>
          </div>
          <div>
            <div id="KG">
              <svg id="KG_svg"></svg>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="9">
        <el-card class="search-card" style="height: 550px">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold;color: #819d6a;font-size: large">垃圾详情</span>
          </div>
          <div class="entity-name">
            {{nodes.name}}
          </div>
          <el-form label-position="left" label-width="80px" :model="garbage_detail" class="data-detail">
            <el-form-item label="所属小类">
              <el-input v-model="garbage_detail.Min_class"></el-input>
            </el-form-item>
            <el-form-item label="垃圾类别">
              <el-input v-model="garbage_detail.Class"></el-input>
            </el-form-item>
            <el-form-item label="处理方式">
              <el-input v-model="garbage_detail.Way"></el-input>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as d3 from 'd3';
import graphData1 from '../../assets/records_can.json'; // 导入包含图表数据的 JSON 文件
import graphData2 from '../../assets/records_cant.json'; // 导入包含图表数据的 JSON 文件
import graphData3 from '../../assets/records_gan.json'; // 导入包含图表数据的 JSON 文件
import graphData4 from '../../assets/records_shi.json'; // 导入包含图表数据的 JSON 文件
import graphData5 from '../../assets/records_big.json';
import request from "@/utils/request"; // 导入包含图表数据的 JSON 文件

export default {
  name: "knowledge",
  data() {
    return {
      showData:[
        {
          name:'垃圾类别',
          value:5,
          color:'#C990C0',
        },
        {
          name:'垃圾小类',
          value:42,
          color:'#F16667',
        },
        {
          name:'具体垃圾',
          value:3985,
          color:'#57C7E3',
        },
        {
          name:'垃圾处理方式',
          value:8,
          color:'#F79767',
        }
      ],
      garbage_detail:{
        Min_class:'',
        Class:'',
        Way:'',
        Garbage:''
      },
      nodes:{
        id:'',
        name:'',
        link_id:'',
      },
      // graph:null,
      // chartData: null,
      ColorList: {
        Class: '#C990C0',
        Min_class: '#57C7E3',
        Garbage: '#F79767',
        Way: '#F16667',
      },
      height: 520,
      width: 800,
      r: 30,
      label: ['Class', 'Min_class', 'Garbage', 'Way'],
      searchnodes: [],
      searchlinks: [],
      simulation: null,
      node: [],
      link: [],
      nodeText: null,
      marker: null,
      nodeinfo: {
        id: '',
        label: '',
        properties: {
          id: '',
          name: '',
          meaning: '',
          link: '',
        },
      },
    };
  },
  methods:{
    loadGraphData1() {
      console.log('我调用了');
      // 加载新的图谱数据
      this.processGraphData(graphData1);
    },
    loadGraphData2() {
      // 加载新的图谱数据
      this.processGraphData(graphData2);
    },
    loadGraphData3() {
      // 加载新的图谱数据
      this.processGraphData(graphData3);
    },
    loadGraphData4() {
      // 加载新的图谱数据
      this.processGraphData(graphData4);
    },
    loadGraphData5() {
      // 加载新的图谱数据
      this.processGraphData(graphData5);
    },
    draw() {
      d3.select("#KG_svg").selectAll("*").remove();

      const ColorList = this.ColorList; // 获取颜色列表
      const width = this.width;
      const height = this.height;
      const r = this.r;
      const searchnodes = this.searchnodes;
      const searchlinks = this.searchlinks;


      const drag = (simulation) => {
        const dragstarted = (event) => {
          if (!event.active) simulation.alphaTarget(0.3).restart();
          event.subject.fx = event.subject.x;
          event.subject.fy = event.subject.y;
        };

        const dragged = (event) => {
          event.subject.fx = event.x;
          event.subject.fy = event.y;
        };

        const dragended = (event) => {
          if (!event.active) simulation.alphaTarget(0);
          event.subject.fx = null;
          event.subject.fy = null;
        };

        return d3.drag()
            .on('start', dragstarted)
            .on('drag', dragged)
            .on('end', dragended);
      };

      const zoomed = (transform) => {
        g.attr('transform', transform);
      };

      const zoom = d3.zoom()
          .scaleExtent([0.2, 4]) // 设置最大缩放比例
          .on('zoom', (d) => {
            zoomed(d.transform);
          });

      const sim = d3.forceSimulation(searchnodes)
          .alphaDecay(0.5)
          .force('link', d3.forceLink(searchlinks).id((n) => n.id).distance(200))
          .force('center', d3.forceCenter(width / 2, height / 2))
          .force('collide', d3.forceCollide(50).iterations(10))
          .force('charge', d3.forceManyBody().strength(-200))
          .force('x', d3.forceX(width / 3).strength(0.05)) // 设置x方向的吸引力
          .force('y', d3.forceY(height / 3).strength(0.05)); // 设置y方向的吸引力

      const svg = d3.select('#KG_svg')
          .style('width', width)
          .style('height', height)
          .style('background-color', '#F9FCFF')
          .call(zoom);

      const g = svg.append('g');

      const link = g.selectAll('path')
          .data(searchlinks)
          .enter()
          .append('path')
          .attr('class', 'links')
          .style('stroke', '#999')
          .style('stroke-width', 2)
          .attr('marker-end', 'url(#direction)');

      const node = g.selectAll('circle')
          .data(searchnodes)
          .enter()
          .append('circle')
          .attr('class', 'nodes')
          .attr('id', (d) => d.id)
          .attr('r', (d) => {
            if (d.label === 'Class') return 70;
            else if (d.label === 'Min_class') return 50;
            else return r;
          })
          .style('fill', function(d) {
            // console.log('Node type:', d.label); // 打印节点类型
            // console.log('Node color:', ColorList[d.label]); // 打印节点颜色
            return ColorList[d.label];
          }) // 使用普通匿名函数来设置颜色
          .style('stroke', 'white')
          .style('stroke-width', 2)
          .call(drag(sim));

      node.append('title').text((d) => d.properties.name);

      const nodeText = g.selectAll('text')
          .data(searchnodes)
          .join('text')
          .attr('dy', '.3em')
          .attr('class', 'node-name')
          .attr('text-anchor', 'middle')
          .style('pointer-events', 'none')
          .text((d) => d.properties.name);

      const marker = g.append('marker')
          .attr('id', 'direction')
          .attr('refX', 35)
          .attr('refY', 0)
          .attr('orient', 'auto')
          .attr('stroke-width', 2)
          .attr('markerUnits', 'strokeWidth')
          .attr('markerUnits', 'userSpaceOnUse')
          .attr('viewBox', '0 -5 10 10')
          .attr('markerWidth', 12)
          .attr('markerHeight', 12)
          .append('path')
          .attr('d', 'M 0 -5 L 10 0 L 0 5')
          .attr('fill', '#999')
          .attr('stroke-opacity', 0.6);

      sim.on('tick', () => {
        link.attr('d', (d) => `M ${d.source.x} ${d.source.y} L ${d.target.x} ${d.target.y}`);
        node.attr('cx', (d) => d.x).attr('cy', (d) => d.y);
        nodeText.attr('x', (d) => d.x).attr('y', (d) => d.y);
      });

      this.simulation = sim;
      this.link = link;
      this.node = node;
      this.nodeText = nodeText;
      this.marker = marker;
      this.addClickEventToNodes();

    },
    processGraphData(graphData) {
      const nodes = [];
      const links = [];
      const nodeSet = [];

      for (let item of graphData) {
        // console.log('lk',item);
        for (let segment of item.p.segments) {
          if (nodeSet.indexOf(segment.start.identity) === -1) {
            nodeSet.push(segment.start.identity);
            nodes.push({
              id: segment.start.identity,
              label: segment.start.labels[0],
              properties: segment.start.properties,
            });
          }
          if (nodeSet.indexOf(segment.end.identity) === -1) {
            nodeSet.push(segment.end.identity);
            nodes.push({
              id: segment.end.identity,
              label: segment.end.labels[0],
              properties: segment.end.properties,
            });
          }
          links.push({
            source: segment.relationship.start,
            target: segment.relationship.end,
            type: segment.relationship.type,
            properties: segment.relationship.properties,
          });
        }
      }

      this.searchlinks = links;
      this.searchnodes = nodes;
      // console.log('aaaa',this.searchlinks,this.searchnodes);
      this.draw();
    },
    addClickEventToNodes() {
      const self = this;
      d3.selectAll('.nodes')
          .on('click', function(d) {
            // 在这里可以获取节点的详细信息，并进行相应的处理
            console.log('Clicked node:', d);
            // 你可以在这里调用方法，将节点的详细信息传递给其他组件或进行其他操作
            self.handleNodeClick(d);
          });
    },
    handleNodeClick(node) {
      // 在这里处理节点点击事件，可以将节点的详细信息传递给其他组件或进行其他操作
      console.log('Node clicked:', node);
      this.garbage_detail.Garbage = '';
      this.garbage_detail.Min_class = '';
      this.garbage_detail.Class = '';
      this.garbage_detail.Way = '';
      this.nodes.name = node.srcElement.__data__.properties.name;
      const type = node.srcElement.__data__.label;
      if(type == 'Min_class'){
        this.garbage_detail.Min_class = node.srcElement.__data__.properties.name;
      }else if(type == 'Class'){
        this.garbage_detail.Class = node.srcElement.__data__.properties.name;
      }else if(type == 'Way'){
        this.garbage_detail.Way = node.srcElement.__data__.properties.name;
      }else{
        this.garbage_detail.Garbage = node.srcElement.__data__.properties.name;
      }
      request.get("classification/getclass",{
        params:{
          pageNum:1,
          pageSize: 8,
          name: this.garbage_detail.Garbage,
          min_class: this.garbage_detail.Min_class,
          classname: this.garbage_detail.Class,
          handle: this.garbage_detail.Way
        }
      }).then(res=>{
        console.log('load',res)
        if(res.records.length!=0){
          this.garbage_detail.Way = res.records[0].handle;
          this.garbage_detail.Class = res.records[0].classname;
          this.garbage_detail.Min_class = res.records[0].minClassNum;
        }
      })
    },
  },
  mounted() {
    this.loadGraphData1(); // 初始加载第一个 JSON 文件
    this.addClickEventToNodes();
  },
}
</script>

<style scoped>
.all-show{
  height: 100%;
  width: 100%;
}
.data-show{
  width: 22%;
  height:120%;
  margin-right: 20px;
  margin-left: 20px;
}
.data-show-detail{
  padding-top: 20px;
  padding-bottom: 20px;
}
.el-statistic{
  font-weight: bold;
  font-size: 20px;
  color: #e5e5e5;
}
/*卡片*/
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}
.clearfix{
  padding-bottom: -5px;
}

.box-card {
  width: 100%;
  height:130%;
  /*height: 100%;*/
  margin-top: 20px;
  margin-left: 5px;
}
.search-card{
  width: 100%;
  height:130%;
  margin-top: 20px;
  margin-left: 25px;
}
.card-row {
  display: flex;
  align-items: stretch;
}
.entity-name{
  border-radius: 30px;
  border: 2px solid #819d6a;
  margin-bottom: 5px;
  height: 40px;
  padding-top: 15px;
  font-weight: bold;
  font-size: 20px;
}

.data-detail{
  margin-top: 20px;
}
.filter_button{
  margin-right: 15px;
  background-color: #a3d48e;
  height: 45px;
  width: 150px;
  border: #a3d48e;
  border-radius: 30px;
}
.filter_button:hover{
  background-color: #819d6a;
}
.el-card__header{
  padding:10px 10px;
}

</style>
