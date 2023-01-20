package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.ResultVO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @ResponseBody
    @CrossOrigin
    @GetMapping(value = "/api/GetAll")
    public List<User> all() throws Exception{
        return userService.getAll();
    }

    @PostMapping(value = "/api/Change")
    @CrossOrigin
    @ResponseBody
    public ResultVO addOrUpdate(@RequestBody User user) throws Exception{
        int res = userService.addOrUpdateUser(user);
        if(res == 1){
            return new ResultVO(200);
        }
        else{
            return new ResultVO(400);
        }
    }

    @PostMapping(value = "/api/Delete")
    @CrossOrigin
    @ResponseBody
    public ResultVO delete(@RequestBody User user) throws Exception{
        int res = userService.delete(user);
        if(res == 1){
            return new ResultVO(200);
        }
        else{
            return new ResultVO(400);
        }
    }
}
