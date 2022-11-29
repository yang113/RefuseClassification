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
    name: 'HomeView',
    component: HomeView,
    children: [
      {
        path: '/home1',
        name:'首页',
        component:() => import('../views/home/Home')
      },
      {
        path: '/money1',
        name:'缴费管理',
        component:() => import('../views/system/Money')
      },
      {
        path: '/caract1',
        name: '车务活动',
        component:() => import('../views/command/Caract')
      },
      {
        path: '/command1',
        name:'车务提醒',
        component:() => import('../views/command/Command')
      },
      {
        path: '/traffic1',
        name: '车务待办',
        component:() => import('../views/command/Traffic')
      },
      {
        path: '/maps1',
        name:'地图',
        component:() => import('../views/help/maps')
      },
      {
        path: '/rescuerecords1',
        name: '救援记录',
        component:() => import('../views/help/RescueRecords')
      },
      {
        path: '/carinfo1',
        name: '救援车辆',
        component:() => import('../views/help/carinfo')
      },
      {
        path:'/travelinfo1',
        name: '旅行记录',
        component:() => import('../views/travell/travellinfo')
      },
      {
        path: '/travel1',
        name:'旅行信息',
        component:()=>import('../views/travell/travel')
      },
      {
        path: '/Vip1',
        name: '会员信息',
        component:() => import('../views/system/Vip')
      }
    ]
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
        component:() => import('../views/department/User')
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
        component:() => import('../views/command/Traffic')
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
      },
      {
        path: '/Vip',
        name: '会员信息',
        component:() => import('../views/system/Vip')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
