<template>
  <div id="container" style="width: 100%;height: 80vh"></div>
</template>

<script>
export default {
  name: "map",




  mounted() {
    var map = new AMap.Map("container", {
      zoom: 13,
      center:[117.058182,39.236622]
    })
    var marker = new AMap.Marker({
      position: new AMap.LngLat(117.058182,39.236622),
      offset: new AMap.Pixel(-10, -10),
      title: '河北工业大学'
    })
    var clickHandler = function(e) {
      alert('['+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]可能需要救援！');
    };

// 绑定事件
    map.on('click', clickHandler);
    map.add(marker);
  }
}
</script>

<style scoped>
#container {width:300px; height: 180px; }

</style>