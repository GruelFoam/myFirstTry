<template>

  <div>
    <el-form class="login-container" label-position="left"
            label-width="0px">
      <h3 class="login_title">系统登录</h3>
      <el-form-item>
        <el-input type="text" v-model="loginForm.username"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="handleLogin">登录</el-button>
      </el-form-item>
    </el-form>
    <div id="n">{{tips}}</div>
    <!-- <div id="te">{{test}}</div> -->
  </div>

</template>

<script>
import * as Cookies from 'js-cookie'

  export default {
    name: 'Login',

    data:function () {
      return {
        loginForm: {
          username: 'Reed',
          password: '123456'
        },
        responseResult: [],
        tips: ''
      }
    },

    methods: {
      handleLogin () {
        // var _this = this
        // console.log(this.$store.state)

        // try{
        //   const data = {
        //     username: this.loginForm.username,
        //     password: this.loginForm.password
        //   }
        //   const res = this.fetchData('login', data)
        //   if(res.date.code == 200){
        //     const cookieToken = res.data.token;
        //     if(!cookieToken){
        //       return
        //     }
        //     Cookies.set('myToken', cookieToken);
        //     this.$router.replace('/index')
        //   }
        // }catch(e){
        //   return false;
        // }
        

        const data = {
          username: this.loginForm.username,
          password: this.loginForm.password
          //此处将key省略？
        }

        // console.log(this.$myHeader())


        this.$axios
          .post('/login', data, this.$myHeader())
          .then(res => {
            // console.log(res)
            if (res.data.code === 200) {
              const cookieToken = res.data.token
              if(!cookieToken){
                return
              }
              Cookies.set('myToken', cookieToken)
              this.$router.replace('/index')
              // _this.$store.commit('login', _this.loginForm)
              // var path = this.$route.query.redirect
              // this.$router.replace({
              //   path: path === '/' || path === undefined ? '/index' : path
              // })
            }
            else{
              this.tips = "错误"
            }
          })
          .catch(failResponse => {
          })
      }
    }
  }
</script>



<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  #n{
    height: 15px;
    border: black 2px solid;
  }

</style>
