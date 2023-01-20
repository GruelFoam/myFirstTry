package com.example.demo.mapper;

import com.example.demo.WithDatabaseApplication;
import com.example.demo.common.utils.JwtUtil;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WithDatabaseApplication.class)
public class MybatisTest {

    @Autowired
    private UserService userService;
    @Test
    public void test(){

//        System.out.println(userService.getAll());


//        userService.addOrUpdateUser(user);

//        userService.showOne("Reed");
//        System.out.println(userService.add(newUserVO));

        User user = new User();
        user.setUid(2);
        user.setPassword("123456");
        System.out.println(userService.delete(user));
//        System.out.println(userService.delete(7));
//
//        User u1 = userService.showOne("Reed");
//        String token = JwtUtil.generateToken("Reed");//stop here
//        System.out.println(token);
//        Claims claims = JwtUtil.getClaimFromToken(token);
//        System.out.println(JwtUtil.getUserName("eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsImNyZWF0ZWQiOjE2NzM4Mzc4NzE3NjgsInRhZyI6MSwiZXhwIjoxNjczODM3OTMxLCJ1c2VybmFtZSI6IlJlZWQifQ.H2XpXjz77b8WntPZbSZkH_Gnh3pAg2n5tXc-rqvpDNe-Rf0FmfERqCX8p9QQBbyV9lzTqPbCixEducCR9EYtvg"));
    }
}
