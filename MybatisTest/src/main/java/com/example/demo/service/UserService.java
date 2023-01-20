package com.example.demo.service;


import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.model.RequesterVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        List<User> userList = userMapper.findAll();
        System.out.println("get all users");
//        for(User user : userList){
//            System.out.println(user);
//        }
        return userList;
    }

    public int addOrUpdateUser(User user){
//        boolean check = myCheck(user);
        int res;
        if(user.getUid() != 0){
            res = userMapper.updateUser(user);
        }
        else{
            res = userMapper.addUser(user);
        }
        System.out.println("add or update a user");
        return res;
    }

    public int delete(User user){
        boolean check = myCheck(user);
        if(check){
            int res = userMapper.deleteUser(user.getUid());
            return res;
        }
        else{
            return 0;
        }
    }

    public User showOne(String username){
        User user = userMapper.findOne(username);
        System.out.println(user);
        return user;
    }




    private boolean myCheck(User user){//判断uid和密码
        int u1 = user.getUid();
        String p1 = user.getPassword();
        List<User> userList = userMapper.findAll();
        for(User u: userList){
            int u2 = u.getUid();
            String p2 = u.getPassword();
            if(u1 == u2 && p1.equals(p2)){
                return true;
            }
        }
        return false;
    }



//    public String login(RequesterVO requesterVO){
//        if (requesterVO.getUsername() == null || requesterVO.getPassword() == null){
//            System.out.println("null");
//        }
//
//    }





//    public List<User> getAll(){
//        sqlSession = MybatisUtils.getSqlSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = userMapper.findAll();
//        for(User user : userList){
//            System.out.println(user);
//        }
//        sqlSession.close();
//        return userList;
//    }
//
//    public int add(User user){
//        sqlSession = MybatisUtils.getSqlSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        int res = userMapper.addLine(user/*line.getUid(), line.getUsername(), line.getAge()*/);
//        sqlSession.commit();
//        sqlSession.close();
//        return res;
//    }
//
//    public int delete(int uid){
//        sqlSession = MybatisUtils.getSqlSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        int res = userMapper.deleteLine(uid);
//        sqlSession.commit();
//        sqlSession.close();
//        return res;
//    }
//
//    public User showOne(int uid){
//        sqlSession = MybatisUtils.getSqlSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        User user = userMapper.findOne(uid);
//        System.out.println(user);
//        return user;
//    }
}
