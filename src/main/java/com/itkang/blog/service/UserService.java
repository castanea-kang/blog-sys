package com.itkang.blog.service;


import com.itkang.blog.entity.User;

/**
 * Created by mis on 2018/12/14.
 */
public interface UserService {

    User findByUserName(String username);
    User findByUsernameAndPassword(String username,String password);
    User findById(Integer userId);
}
