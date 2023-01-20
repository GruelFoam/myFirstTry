<template>
    <el-container>
        <el-header>
            <h2>用户</h2>
        </el-header>
        <el-main>
            <el-col :span="4" v-for="(Info, index) in InfoList" :key="index" style="margin: 10px 10px 10px 10px">
                <div class="grid-content bg-purple">
                    <el-card class="box-card">
                        <div class="text item">
                            {{Info.uid}}
                            <br/>
                            {{Info.username}}
                            {{Info.age}}
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-main>
        <div style="float: left; width: 100px; margin: 0 0 0 30px">
            <el-button @click="dialogFormVisible1 = true" type="primary" icon="el-icon-edit" style="width: 100px">新建</el-button>
            <el-dialog title="新建用户" :visible.sync="dialogFormVisible1" style="width: 1500px">
                <el-form v-model="NewUserForm">
                    <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input v-model="NewUserForm.username" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="NewUserForm.password" autocomplete="off" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="NewUserForm.age" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible1 = false">取 消</el-button>
                    <el-button type="primary" @click="newSubmit">确 定</el-button>
                </div>
            </el-dialog>
        </div>


        <div style="float: left; width: 100px; margin: 20px 0 0 30px">
            <el-button @click="dialogFormVisible2 = true" type="primary" icon="el-icon-edit" style="width: 100px">修改</el-button>
            <el-dialog title="修改用户" :visible.sync="dialogFormVisible2" style="width: 1500px">
                <el-form v-model="NewUserForm">
                    <el-form-item label="uid" :label-width="formLabelWidth">
                    <el-input v-model="UpDateUserForm.uid" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                    <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input v-model="UpDateUserForm.username" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="UpDateUserForm.password" autocomplete="off" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="UpDateUserForm.age" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                    <el-button type="primary" @click="updateSubmit">确 定</el-button>
                </div>
            </el-dialog>
        </div>


        <div style="float: left; width: 100px; margin: 20px 0 0 30px">
            <el-button @click="dialogFormVisible3 = true" type="primary" icon="el-icon-edit" style="width: 100px">删除</el-button>
            <el-dialog title="删除用户" :visible.sync="dialogFormVisible3" style="width: 1500px">
                <el-form v-model="NewUserForm">
                    <el-form-item label="uid" :label-width="formLabelWidth">
                    <el-input v-model="DeleteUserForm.uid" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                    <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input v-model="DeleteUserForm.username" autocomplete="off" type="text"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="DeleteUserForm.password" autocomplete="off" type="password"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible3 = false">取 消</el-button>
                    <el-button type="primary" @click="deleteSubmit">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </el-container>
</template>

<script>
import * as Cookies from 'js-cookie'

    export default {
        
        inject: ['reload'],
        name: 'UserIndex',
        data() {
            return {
                Info: {
                    uid: '',
                    username: '',
                    age: ''
                },
                InfoList: [],
                dialogFormVisible1: false,
                dialogFormVisible2: false,
                dialogFormVisible3: false,
                NewUserForm: {
                    username: '',
                    password: '',
                    age: ''
                },
                UpDateUserForm: {
                    uid: '',
                    username: '',
                    password: '',
                    age: ''
                },
                DeleteUserForm: {
                    uid: '',
                    username: '',
                    password: ''
                },
                formLabelWidth: '120px'
            }
        },
        created () {
            this.$axios.get('/api/GetAll', this.$myHeader())
                .then(response =>{
                    console.log(response)
                    this.InfoList = response.data
                    if(response.data.length == 0){
                        this.$myClear()
                    }
                    // console.log(response.data)
                    // alert("成功")
                })
        },
        methods: {
            // chear() {
            //     this.NewUserForm = {
            //         NewUserForm: {
            //             username: '',
            //             password: '',
            //             age: ''
            //         }
            //     }
            // },
            newSubmit() {
                this.$axios
                .post('/api/Change',
                    {username: this.NewUserForm.username,
                    password: this.NewUserForm.password,
                    age: this.NewUserForm.age},
                    this.$myHeader()
                )
                .then(response => {
                    if(response.data.code === 200) {
                        this.dialogFormVisible = false
                        this.reload();
                        this.$message({
                            message: '创建成功',
                            type: 'success'
                        });
                        this.$emit('newSubmit')
                    }
                    else{
                        this.$myClear()
                    }
                })
            },
            updateSubmit() {
                this.$axios
                .post('/api/Change',
                    {uid: this.UpDateUserForm.uid,
                    username: this.UpDateUserForm.username,
                    password: this.UpDateUserForm.password,
                    age: this.UpDateUserForm.age},
                    this.$myHeader()
                )
                .then(response => {
                    if(response.data.code === 200) {
                        this.dialogFormVisible = false
                        this.reload();
                        this.$message({
                            message: '更改成功',
                            type: 'success'
                        });
                        this.$emit('updateSubmit')
                    }
                    else{
                        this.$myClear()
                    }
                })
            },
            deleteSubmit() {
                this.$axios
                .post('/api/Delete',
                    {uid: this.DeleteUserForm.uid,
                    username: this.DeleteUserForm.username,
                    password: this.DeleteUserForm.password},
                    this.$myHeader()
                )
                .then(response => {
                    if(response.data.code === 200) {
                        this.dialogFormVisible = false
                        this.reload();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.$emit('deleteSubmit')
                    }
                    else{
                        this.$myClear()
                    }
                })
            }
        }
        //     open() {
        //     this.$prompt('请输入邮箱', '新建用户', {
        //             confirmButtonText: '确定',
        //             cancelButtonText: '取消',
        //             inputPattern: '',
        //             inputErrorMessage: '邮箱格式不正确'
        //         }).then(({ value }) => {
        //         this.$message({
        //             type: 'success',
        //             message: '你的邮箱是: ' + value
        //         });
        //         }).catch(() => {
        //         this.$message({
        //             type: 'info',
        //             message: '取消输入'
        //         });       
        //         });
        //     }

        // mounted (){
            // this.$axios.get('/Users')
            //     .then(response =>{
            //         this.InfoList = response.data
            //         console.log(response.data)
            //         // alert("成功")
            //     })
                // .catch(function (error) {
                //     alert("失败")
                // })
        // }
    }
</script>