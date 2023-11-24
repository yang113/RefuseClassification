import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import request from "@/utils/request";
import VCharts from 'v-charts'
import VeLine from 'v-charts/lib/line.common'
import VueAMap from 'vue-amap';
// 引入 echarts 插件
import echarts from 'echarts'

import "lib-flexible"

// 配置成全局组件
Vue.prototype.$echarts = echarts
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: 'your amap key',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor'],
  v: '1.4.4'
});


Vue.config.productionTip = false
Vue.prototype.request = request()
Vue.use(VCharts)
Vue.component(VeLine.name, VeLine)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
