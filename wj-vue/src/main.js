
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import fetchData from './api/http'
import * as Cookies from 'js-cookie'
import {myHeader, myClear} from './utils/myFunc'


// 设置反向代理，前端请求默认发送到 http://localhost:8443/api
import axios from 'axios'
//var axios = require('axios')

Vue.prototype.$myHeader = myHeader
Vue.prototype.$myClear = myClear
Vue.prototype.fetchData = fetchData
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://localhost:8433'

Vue.config.productionTip = false

Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  const token = Cookies.get('myToken');

  // 是否为免验证路由 true：免验证token false：需验证token

  if (token) {
    next()
  } else {
      if (to.path === '/login') {
          next()
      } else {
          next('/login')
      }
  }
})


//   if ((to.meta.requireAuth)) {
//     if (store.state.user.username) {
//       next()
//     } else {
//       next({
//         path: 'login',
//         query: {redirect: to.fullPath}
//       })
//     }
//   } else {
//     next()
//   }
// })


/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})

