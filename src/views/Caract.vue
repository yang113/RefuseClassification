<template>
<div>
  <el-row>
    <el-col :span="8">
      <el-card :body-style="{ padding: '0px' }">
        <img src="../assets/carcat1.png" class="image">
        <div style="padding: 14px;">
          <span style="text-align: center">汽车展览</span>
          <div class="bottom clearfix">
            <span>2022年11月28日，汽车展览会将在上海...</span>
            <el-button type="text" class="button"><a href="http://www.wfcvc.com/">查看详情</a></el-button>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="8" >
      <el-card :body-style="{ padding: '0px' }" style="margin-left: 20px;">
        <img src="../assets/carcat2.png" class="image">
        <div style="padding: 14px;">
          <span>汽车免费维护</span>
          <div class="bottom clearfix">
            <span>2022年11月1日，本俱乐部将举办汽车免费...</span>
            <el-button type="text" class="button"><a href="https://www.sohu.com/a/341231667_100014691">查看详情</a></el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "Caract",
  data() {
    return {
      currentDate: new Date()
    };
  }
}
</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 5px 0 0;
  float: right;
}

.image {
  width: 390px;
  /*height: 240px;*/
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>