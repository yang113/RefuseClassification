<template>
  <div id="app">
    <ve-line :data="chartData" :settings="chartSettings"></ve-line>
  </div>
</template>

<script>
import request from "@/utils/request";


export default {
  name: "charts",
  data: function () {
    this.chartSettings = {
      xAxisType: 'category'
    };
    return {
      chartData: {
        columns: ['日期', '销售额'],
        rows: []
      },
      activeName: 'first', //当前tab的name值
      DataShow: [],
    }
  },
  mounted() {
    this.showMoney();
  },
  methods:{
    showMoney(){
      request.get("/money/chart",{
      }).then(res => {
        console.log(res)
        this.DataShow = res;
        console.log(this.DataShow)
        for (var i = 0; i < this.DataShow.length; i++) {
          this.chartData.rows.push({
            "日期": this.DataShow[i].mdate,
            "销售额": this.DataShow[i].money,
          });
        }
      })
    }
  }
}
</script>
