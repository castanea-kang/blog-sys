package com.itkang.blog.mapper;


import com.itkang.blog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {

    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(Map<String,String> param);
    User findUserById(Integer id);

}