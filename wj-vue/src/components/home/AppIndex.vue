<template>
    <div id="a">
      <h1>Hello {{uname}}!</h1>
    </div>
    
</template>

<script>
import store from '../../store'
  export default {
    name: 'AppIndex',
    

    data: function(){
      return{
        uname: store.state.user.username
      }
    },

    created(){
      
    }
    
    
    // created: function(){
    //   this.getParams();
    // },
    // watch: {
    //   '$route': 'getParams'
    // },
    // methods:{
    //   getParams:function(){
    //     var routerParams = new Vue({
    //       el: '#a',
    //       data: {res: this.$router.query.nameId}
    //     })
    //     //console.log("获得：" + routerParams)
    //     //this.textareText = routerParams
    //   }
    // }
  }

  
</script>

<style scoped>

</style>

