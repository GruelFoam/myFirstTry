package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.RequesterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {

    @Autowired
    private UserService userService;

    public boolean myCheck(RequesterVO requesterVO){
        List<User> userList = userService.getAll();
        for(User user: userList){
            String n1 = requesterVO.getUsername();
            String p1 = requesterVO.getPassword();
            String n2 = user.getUsername();
            String p2 = user.getPassword();

            if(n1.equals(n2) && p1.equals(p2)){
                return true;
            }
        }
        return false;
    }
}
