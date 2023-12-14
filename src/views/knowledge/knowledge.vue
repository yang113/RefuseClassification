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
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold;color: #819d6a;font-size: large">垃圾分类图谱</span>
          </div>
          <div>
            <div id="graph" style="width:100%;height:500px"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="9">
        <el-card class="search-card">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold;color: #819d6a;font-size: large">垃圾详情</span>
          </div>
          <div class="entity-name">
            {{node.name}}
          </div>
          <el-form label-position="left" label-width="80px" :model="garbage_detail" class="data-detail">
            <el-form-item label="结点id">
              <el-input v-model="garbage_detail.id"></el-input>
            </el-form-item>
            <el-form-item label="所属小类">
              <el-input v-model="garbage_detail.link_name"></el-input>
            </el-form-item>
            <el-form-item label="垃圾类别">
              <el-input v-model="garbage_detail.class_name"></el-input>
            </el-form-item>
            <el-form-item label="处理方式">
              <el-input v-model="garbage_detail.way"></el-input>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';
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
        id:1,
        link_id:3,
        link_name:'塑料类',
        class_name:'可回收垃圾',
        way:'回收'
      },
      node:{
        id:1,
        name:'塑料袋',
        link_id:1,
      },
      graph: "",
      pointData: [],
      linkData: [],
      categoryData: [],
      options: {
        title: {
          text: "知识图谱示例"
        },
        series: [
          {
            name: "知识图谱",
            type: "graph",
            layout: "force",
            force: {
              repulsion: 60,
              gravity: 0.1,
              edgeLength: 15,
              layoutAnimation: true
            },
            data: [],
            links: [],
            categories: [],
            roam: false,
            symbol: 'circle',
            symbolSize: 40,
            cursor: 'pointer',
            label: {
              normal: {
                show: true,
                position: "inside",
                formatter: "{b}",
                fontSize: 16,
                fontStyle: "600"
              }
            },
            lineStyle: {
              normal: {
                opacity: 0.9,
                width: 1.5,
                curveness: 0
              }
            }
          }
        ]
      }
    };
  },
  methods: {
    setPointData(list, category) {
      list.forEach((name, index) => {
        this.pointData.push({
          x: index * 50,
          y: 20 + index * 50,
          name,
          category,
          draggable: true
        });
      });
    },
    setLinkData(list, target) {
      list.forEach(source => {
        this.linkData.push({
          source,
          target,
          lineStyle: {
            normal: {
              color: "source"
            }
          }
        });
      });
    },
    setCategoryData(list) {
      list.forEach(name => {
        this.categoryData.push({ name });
      });
    }
  },
  created() {},
  mounted() {
    this.graph = echarts.init(document.getElementById("graph"));
    let pointList1 = ["红", "红1", "红2", "红3", "红4"];
    let pointList2 = ["蓝", "蓝1", "蓝2", "蓝3", "蓝4"];
    let pointList3 = ["黄", "黄1", "黄2", "黄3", "黄4"];
    let pointList4 = ["颜色"];
    this.setPointData(pointList1, "红");
    this.setPointData(pointList2, "蓝");
    this.setPointData(pointList3, "黄");
    this.setPointData(pointList4, "颜色");
    this.setLinkData(pointList1, "红");
    this.setLinkData(pointList2, "蓝");
    this.setLinkData(pointList3, "黄");
    this.setLinkData(["红", "蓝", "黄"], "颜色");
    this.setCategoryData(["红", "蓝", "黄", "颜色"]);
    this.options.series[0].data = this.pointData;
    this.options.series[0].links = this.linkData;
    this.options.series[0].categories = this.categoryData;
    // this.options.legend.data = this.categoryData;
    this.$nextTick(() => {
      window.console.log(this.options);
      this.graph.setOption(this.options);
    });
  }
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

</style>
