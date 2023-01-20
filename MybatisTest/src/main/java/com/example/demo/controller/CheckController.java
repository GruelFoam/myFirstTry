package com.example.demo.controller;

import com.example.demo.common.utils.JwtUtil;
import com.example.demo.entity.User;
import com.example.demo.model.RequesterVO;
import com.example.demo.model.ResultVO;
import com.example.demo.model.TokenVO;
import com.example.demo.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CheckController {
    @Autowired
    private CheckService checkService;


    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public ResultVO login(@RequestBody RequesterVO requesterVO){
//        String username = requester.getUsername();
//        username = HtmlUtils.htmlEscape(username);
//        CheckService checkService = new CheckService();

        boolean check = checkService.myCheck(requesterVO);
        if(check) {
            System.out.println("true");
            return new ResultVO(200, JwtUtil.generateToken(requesterVO.getUsername()));
        }else{
            System.out.println("false" + requesterVO.getUsername() + requesterVO.getPassword());
            return new ResultVO(400);
        }


//        if(!(Objects.equals("root", username)) || !(Objects.equals("123456", requester.getPassword()))){
//            System.out.println("false");
//            return new Result(400);
//        }else {
//            System.out.println("true");
//            return new Result(200);
//        }
    }
}
