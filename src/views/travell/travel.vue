<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <img src="../../assets/travel1.png" class="image">
          <div style="padding: 14px;">
            <span style="text-align: center">天津--西安</span>
            <div class="bottom clearfix">
              <span>探秘兵马俑、博物馆奇遇、全家互动古装剧</span>
              <el-button type="text" class="button"><a href="https://zjjl4.package.qunar.com/user/detail.jsp?id=862920468&tm=2lou_miaosha_origin#tm=2lou_miaosha_origin">查看详情</a></el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8" >
        <el-card :body-style="{ padding: '0px' }" style="margin-left: 20px;">
          <img src="../../assets/travel2.png" class="image">
          <div style="padding: 14px;">
            <span>北京———厦门</span>
            <div class="bottom clearfix">
              <span>国际大牌五钻酒店任选+可免费住鼓浪屿高档型酒店</span>
              <el-button type="text" class="button"><a href="https://sxzl0.package.qunar.com/user/detail.jsp?id=3806994996&tm=2lou_miaosha_origin#tm=2lou_miaosha_origin">查看详情</a></el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "travel",
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