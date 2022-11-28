<template>
  <div>
    <h3 class="title">{{ msg }}</h3>
    <div class="amap-wrapper">
      <el-amap class="amap-box" :vid="'amap-vue'" dragEnable="false"></el-amap>
    </div>
  </div>
</template>
<script>
export default {
  name: "maps",
  data () {
    return {
      msg: '救援服务点详情'
    }
  }
}
</script>

<style scoped>
.amap-wrapper {
  width: 1200px;
  height: 500px;
  margin: 0 auto;
}
</style>