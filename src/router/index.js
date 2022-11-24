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
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
