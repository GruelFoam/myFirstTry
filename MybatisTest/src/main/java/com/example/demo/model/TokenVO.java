package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenVO implements Serializable {
    private String username;
    public TokenVO(String username){
        this.username = username;
    }
}
