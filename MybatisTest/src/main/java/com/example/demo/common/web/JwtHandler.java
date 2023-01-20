package com.example.demo.common.web;

import com.example.demo.common.utils.JwtUtil;
import com.example.demo.entity.User;
import com.example.demo.model.TokenVO;
import com.example.demo.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtHandler implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        TokenVO tokenVO = JwtUtil.getUserFromToken(request);//JwtUtil中在此处省略了两个函数
//        System.out.println(tokenVO);//打印tokenVO对象
        if(tokenVO == null){
            System.out.println("token无效");
            return false;
        }
        User user = userService.showOne(tokenVO.getUsername());
        if(user == null){
            System.out.println("找不到该用户");
            return false;
        }
        return true;
    }
}
