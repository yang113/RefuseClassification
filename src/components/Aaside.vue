<template>
  <el-menu :default-openeds="[]" style="height: 100%;overflow-x: hidden"
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
    <el-menu-item index="/ahome">
      <i class="el-icon-s-home"></i>
      <span>主页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>系统管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/auser">员工管理</el-menu-item>
        <el-menu-item index="/depart">部门管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aaside"
}
</script>

<style scoped>

</style>