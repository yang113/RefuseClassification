<template>
  <div id="main" class="main">
    <el-carousel :interval="4000" type="card">
      <el-carousel-item v-for="item in 6" :key="item" ></el-carousel-item>
    </el-carousel>
    <span>
      <el-row class="image-row" type="flex" >
        <el-col :span="4" v-for="(item, index) in garbage_data" :key="index" class="garbage-class">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="item.src" class="image">
            <div style="padding: 5px;">
              <span>{{ item.name }}</span>
              <div class="bottom clearfix">
                <text>{{ item.ext }}</text>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <div class="slogan">认准垃圾标识，让我们一起把垃圾分分类吧！</div>
      <div class="copyright">版权所有：汪子茵、刘紫暄、韩文华、杜睿凯</div>
    </span>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return {
      garbage_data:[{
        name:'可回收垃圾',
        ext:'可回收标志',
        src:require('../../assets/can1.png'),
      },{
        name:'有害垃圾',
        ext:'有害垃圾标志',
        src:require('../../assets/can2.png')
      },{
        name:'湿垃圾',
        ext:'湿垃圾标志',
        src:require('../../assets/can3.png'),
      },{
        name:'干垃圾',
        ext:'干垃圾标志',
        src:require('../../assets/can4.png')
      },{
        name:'大件垃圾',
        ext:'大件垃圾标志',
        src:require('../../assets/can5.png')
      }]
    }
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(6n) {
  background: url("../../assets/i1.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.el-carousel__item:nth-child(6n+1) {
  background: url("../../assets/i2.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+2) {
  background: url("../../assets/i3.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+3) {
  background: url("../../assets/i4.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.el-carousel__item:nth-child(6n+4) {
  background: url("../../assets/i5.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.main{
  height: 600px;
}
.copyright{
  margin-top: 10px;
  color: #cccccc;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.image {
  width: 200px;
  height: 200px;
  /*display: block;*/
  position: center;
}
.image-row {
  display: flex;
  flex-wrap: nowrap; /* 控制子元素不换行 */
  margin-left: 5px;
}
.slogan{
  color: #819d6a;
  font-weight: bold;
  font-size: 28px;
  margin-top: 25px;
}
.garbage-class{
  margin-right: 50px;
}
</style>