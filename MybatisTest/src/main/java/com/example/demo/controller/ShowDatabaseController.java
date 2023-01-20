package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ShowDatabaseController {
    @Autowired
    private UserService userService;
    @RequestMapping("/data")
    public List<User> requestData(){
//        UserService userService = new UserService();
        return userService.getAll();
    }

    @RequestMapping("/data/{username}")
    public User requestOneData(@PathVariable String username){
//        UserService userService = new UserService();
        return userService.showOne(username);
    }
}
