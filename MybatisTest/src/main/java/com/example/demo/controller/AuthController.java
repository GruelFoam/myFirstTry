//package com.example.demo.controller;
//
//
//import com.example.demo.model.RequesterVO;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("auth")
//public class AuthController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/Login")
//    public String login(@RequestBody RequesterVO requesterVO){
//        String token = userService.login(requesterVO);
//    }
//}
