<template>
  <div class="wrapper">
    <rain></rain>
    <div class="button-container">
      <el-button type="success" plain size="large" @click="login">游客入口</el-button>
      <el-button type="success" plain size="large" @click="manageLogin">管理入口</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import rain from "../rain";
export default {
  name: "Login",
  components:{
    rain
  },
  methods: {
    login() {
      this.$router.push('/employeeHome')
    },
    manageLogin() {
      this.$router.push('/manageHome')
    }
  }
}
</script>

<style scoped>
.wrapper {
  position: relative; /* 将容器设置为相对定位 */
  height: 100vh;
  background: url("../assets/bg1.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  overflow: hidden;
}

.button-container {
  position: absolute; /* 将按钮容器设置为绝对定位 */
  bottom: 350px; /* 距离底部的距离 */
  left: 20%; /* 水平居中 */
  transform: translateX(-50%); /* 水平居中 */
}

.el-button {
  margin: 0 10px; /* 调整按钮之间的间距 */
  width: 150px;
  height: 50px;
  font-size: 20px;
  font-weight: bold;
  color: #819d6a;
}
</style>