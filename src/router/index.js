import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import HomeView from "@/views/home/HomeView";
import ManageHome from "@/views/home/ManageHome";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/employeeHome',
    name: 'employeeHome',
    component: HomeView,
  },
  {
    path: '/manageHome',
    name: 'manage',
    component: ManageHome,
    children:[
      {
        path: '/home',
        name:'首页',
        component:() => import('../views/home/Home')
      },
      {
        path: '/user',
        name: '用户管理',
        component:() => import('../views/system/User')
      },
      {
        path: '/money',
        name:'缴费管理',
        component:() => import('../views/system/Money')
      },
      {
        path: '/caract',
        name: '车务活动',
        component:() => import('../views/command/Caract')
      },
      {
        path: '/command',
        name:'车务提醒',
        component:() => import('../views/command/Command')
      },
      {
        path: '/charts',
        name:'统计报表',
        component:() => import('../views/statistics/charts')
      },
      {
        path: '/traffic',
        name: '服务信息',
        component:() => import('../views/statistics/Traffic')
      },
      {
        path: '/maps',
        name:'地图',
        component:() => import('../views/help/maps')
      },
      {
        path: '/rescuerecords',
        name: '救援记录',
        component:() => import('../views/help/RescueRecords')
      },
      {
        path: '/carinfo',
        name: '救援车辆',
        component:() => import('../views/help/carinfo')
      },
      {
        path:'/travelinfo',
        name: '旅行记录',
        component:() => import('../views/travell/travellinfo')
      },
      {
        path: '/travel',
        name:'旅行信息',
        component:()=>import('../views/travell/travel')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
