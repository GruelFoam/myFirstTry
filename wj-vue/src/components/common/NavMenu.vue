<template>
    <el-menu
        :default-active="'activeIndex'"
        router
        class="el-menu-demo"
        mode="horizontal"
        >
        <!-- @select="handleSelect" -->
        <el-menu-item index="/Index">首页</el-menu-item>
        <!-- <el-submenu index="/User">
            <template slot="title">功能</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项1</el-menu-item>
            <el-menu-item index="2-4-2">选项2</el-menu-item>
            <el-menu-item index="2-4-3">选项3</el-menu-item>
            </el-submenu>
        </el-submenu> -->
        <el-menu-item index="/Users" >用户</el-menu-item>
        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>

    <!-- <el-tabs v-model="activeName" @tab-click="handleClick" style="margin: 0 300px 0 300px">
    <el-tab-pane label="首页" name="first"></el-tab-pane>
    <el-tab-pane label="用户" name="second"></el-tab-pane>
    <el-tab-pane label="角色管理" name="third">third</el-tab-pane>
    <el-tab-pane label="定时任务补偿" name="fourth"></el-tab-pane>
    </el-tabs> -->


</template>

<script>
    export default {
        name: 'NavMenu',
        data() {
            return {
                activeIndex: '/Index'
            }
        }
    }
</script>
