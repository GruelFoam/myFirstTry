package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO{
    private int code;
    private String token;

    public ResultVO(int c){
        code = c;
    }

}
