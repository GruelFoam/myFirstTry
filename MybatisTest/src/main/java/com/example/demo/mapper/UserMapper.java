package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAll();
    public int addUser(User user);
    public int deleteUser(int uid);
    public User findOne(String username);
    public int updateUser(User user);
}
