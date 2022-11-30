<template>
  <div class="wrapper">
    <rain></rain>
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <div type="flex">
          <el-radio-group v-model="radio" style="margin: 0 auto" type="flex" justify="center">
            <el-radio :label="1">工作人员</el-radio>
            <el-radio :label="2">经理</el-radio>
            <el-radio :label="3">系统管理员</el-radio>
          </el-radio-group>
        </div>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
          <el-button type="warning" size="small"  autocomplete="off">注册</el-button>
        </el-form-item>
      </el-form>
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
  data(){
    return{
      user:{
        user:"su",
        password:"123"
      },
      rules:{},
      radio: 1,
    }
  },
  methods:{
    login(){
      if (this.user.user===""||this.user.password===""){
        this.$message.error("请输入账户或密码")
      }
      else {
        if (this.radio !== 1){
          if(this.radio === 2){
            if(this.user.user=== "1" && this.user.password === "1"){
              this.$message.success("经理登陆成功")
              this.$router.push('/manageHome')
            }
            else {
              this.$message.error("管理员用户名密码错误")
            }
          }
          if (this.radio === 3){
            if(this.user.user=== "2" && this.user.password === "2"){
              this.$message.success("管理员登陆成功")
              this.$router.push('/Admin')
            }
          }
        }
        else {
          request.post("/login",this.user).then(res =>{
            if(!res)
              this.$message.error("用户名密码错误")
            else {
              if (this.radio === 1){
                console.log(res)
                this.$message.success("登陆成功")
                this.$router.push('/employeeHome')
              }
            }
          })
        }
      }

    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background: url("../assets/bg1.png");
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  overflow: hidden;
}
</style>