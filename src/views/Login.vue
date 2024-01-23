<template>
  <div class="wrapper">
    <rain></rain>
    <div class="button-container">
      <el-button type="success" plain size="large" @click="login">游客入口</el-button>
      <el-button type="success" plain size="large" @click="manageLogin">管理入口</el-button>
    </div>
    <el-dialog
        title="登录"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <span>
        <el-form status-icon label-width="80px">
          <el-form-item label="账号" prop="name">
            <el-input v-model="name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="password" autocomplete="off"></el-input>
          </el-form-item>
      </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="login2()" style="color:white;">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import rain from "../rain";
export default {
  name: "Login",
  data(){
    return {
      dialogVisible: false,
      name:'',
      password:''
    };
  },
  components:{
    rain
  },
  methods: {
    login() {
      this.$router.push('/employeeHome')
    },
    manageLogin() {
      this.dialogVisible = true;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.dialogVisible = false;
          })
          .catch(_ => {});
    },
    login2(){
      if(this.name === 'wang' && this.password === '123456'){
        this.dialogVisible = false;
        this.$router.push('/manageHome')
      }else{
        alert('账号或密码错误！')
      }
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