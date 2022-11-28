<template>
  <el-container  style="min-height: 100vh;">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246);height:100vh;
	    box-shadow: 2px 0 6px rgb(0 21 41 / 35%);-webkit-scrollbar:none ">
      <Easide :isCollapse="isCollapse" :logoTextshow="logoTextShow"></Easide>
    </el-aside>

    <el-container>
      <el-header style="border: black 1px solid;">
        <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse"/>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>
import Header from "@/components/Header";
import Easide from "@/components/Easide";
export default {
  name: "HomeView",
  components:{
    Header,
    Easide
  },
  data() {
    return {
      dialogFormVisible: false,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      multipleSelection: []
    }
  },
  methods:{
    collapse(){
      this.isCollapse = !this.isCollapse
      if (this.isCollapse){
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
      }
      else{
        this.sideWidth=200
        this.collapseBtnClass = 'el-icon-s-fold'
      }
    },
  }
}
</script>

<style scoped>

</style>