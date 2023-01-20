import * as Cookies from 'js-cookie'

// const CONFIG = {
//     headers: {
//         'Content-Type': 'application/json;charset=UTF-8',
//         'myToken': Cookies.get('myToken'),
//         'HAHAHA': 'ABC'
//     }
// }

// export default {
//     CONFIG
// }



export function myHeader(){
    // console.log("hahaha")
    return {headers: {'Content-Type': 'application/json;charset=UTF-8',
    'myToken': Cookies.get('myToken'),
    'HAHAHA': 'ABC'}
    }
}


export function myClear(){
    Cookies.remove('myToken')
    this.$router.replace('/index')
}



// export default function myFetch(url, params = {}){
//     const config = {
//         headers: {
//             'Content-Type': 'application/json;charset=UTF-8',
//             'myToken': Cookies.get('myToken'),
//         }
//     }
//     this.$axios
//         .post(url, params, config)
//         .then(response => {
//             const res1 = response.data.code
//             const 
//         })
// }