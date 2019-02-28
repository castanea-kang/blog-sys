package com.itkang.blog.service.impl;

import com.itkang.blog.entity.User;
import com.itkang.blog.mapper.UserMapper;
import com.itkang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by mis on 2018/12/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Map<String,String> param = new HashMap<>();
        param.put("username",username);
        param.put("password",password);
        return userMapper.findUserByUsernameAndPassword(param);
    }

    @Override
    public User findById(Integer userId) {
        return userMapper.findUserById(userId);
    }
}
