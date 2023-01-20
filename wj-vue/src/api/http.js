import Vue from 'vue';
import * as Cookies from 'js-cookie';
import goLogin from '@/utils/goLogin';
// import 'whatwg-fetch';
import apis from '../api/index.js'
import { getQuery } from '../utils/index.js';


const EVENT_BUS = new Vue();
let currentLimiting = '';
function checkStatus (response) {
    currentLimiting = '';
    return response;
}

// 拼接字符串
function obj2Str (obj) {
    var str = '';
    for (let key in obj) {
        str += `${key}=${obj[key]}&`
    }
    return str ? '?' + str : ''
}

function BuFormat (res) {
    currentLimiting = '';
    const { code } = res;
    if (code == 0) {
        return res;
    } else if (code == 10100000) {
        EVENT_BUS.$message.error(res.msg || '账号已登出，请重新登录');
        Cookies.remove('myToken')
        window.localStorage.clear();
        setTimeout(() => {
            goLogin();
        }, 1500);
    } else if (code == 99999999 || code == 10100001 || code == 10100002 || code == 10100003) {
        EVENT_BUS.$message.error(res.msg || '服务器故障了，请稍后重试！');
        return res;
    } else {
        EVENT_BUS.$message.error(res.msg || '服务器故障了，请稍后重试！');
        throw Error();
    }
}
function responseFormat (response) {
    return response.json();
}

export default function fetchData (apiName, params = {}, headers = {}) {
    // const stringURL = apiName + JSON.stringify(params)
    // if (currentLimiting == stringURL) {
    //     // eslint-disable-next-line
    //     return new Promise((resove) => { resove({}) })
    // }
    // currentLimiting = stringURL;
    

    const APICONFIG = Array.isArray(apiName) ? apiName : apis(apiName);
    // if (!APICONFIG) {
    //     console.log(apiName)
    //     EVENT_BUS.$message.error('api接口未定义');
    // }
    let [url, method] = APICONFIG;
    const ajaxConfig = {
        method,
        credentials: 'include',
    };
    if (method === 'GET') {
        url = url + obj2Str(params).slice(0, obj2Str(params).length - 1)
    } else {
        ajaxConfig.body = JSON.stringify(params);
    }
    // set headers
    const setHeader = {
        'Content-Type': 'application/json;charset=UTF-8',
        'myToken': Cookies.get('myToken') || getQuery('myToken'),
    }
    ajaxConfig.headers = Object.assign(setHeader, headers);
    return fetch("http://localhost:8433" + url, ajaxConfig)
        .then(checkStatus)
        .then(responseFormat)
        .then(BuFormat)
}


