import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import AppIndex from '@/components/home/AppIndex'
import Home from '../components/Home'
import UserIndex from '../components/Users/UserIndex'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',//在网页上看到的路径,但会被redirect到/index
      name: 'Home',
      component: Home,//上面import了
      redirect: '/index',
      children:[
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {requireAuth: true}
        },
        {
          path: '/Users',
          name: 'Users',
          component: UserIndex,
          meta: {requireAuth: true}
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
    
  ]
})
