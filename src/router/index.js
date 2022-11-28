import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import HomeView from "@/views/HomeView";
import ManageHome from "@/views/ManageHome";
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
        component:() => import('../views/Home')
      },
      {
        path: '/user',
        name: '用户管理',
        component:() => import('../views/User')
      },
      {
        path: '/money',
        name:'缴费管理',
        component:() => import('../views/Money')
      },
      {
        path: '/caract',
        name: '车务活动',
        component:() => import('../views/Caract')
      },
      {
        path: '/command',
        name:'车务提醒',
        component:() => import('../views/Command')
      },
      {
        path: '/charts',
        name:'统计报表',
        component:() => import('../views/charts')
      },
      {
        path: '/traffic',
        name: '服务信息',
        component:() => import('../views/Traffic')
      },
      {
        path: '/maps',
        name:'地图',
        component:() => import('../views/maps')
      },
      {
        path: '/rescuerecords',
        name: '救援记录',
        component:() => import('../views/RescueRecords')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
