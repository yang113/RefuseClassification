<template>
  <el-menu :default-openeds="['1', '3']" style="height: 100%;overflow-x: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 6px;margin-right: 5px">
      <b style="color: white" v-show="!isCollapse">管理员后台</b>
    </div>
    <el-menu-item index="/home">
      <i class="el-icon-s-home"></i>
      <span>主页</span>
    </el-menu-item>
    <el-submenu index="/">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>系统管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/user">用户管理</el-menu-item>
      </el-menu-item-group>
      <el-menu-item index="/money">缴费管理</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse:Boolean,
    logoTextshow:Boolean
  }
}
</script>

<style scoped>

</style>