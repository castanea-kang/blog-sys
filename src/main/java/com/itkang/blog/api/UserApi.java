package com.itkang.blog.api;


import com.itkang.blog.common.Result;
import com.itkang.blog.entity.User;
import com.itkang.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mis on 2018/12/14.
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户", nickname = "likang")
    @RequestMapping("/getInfo")
    public Result list(String username) {
        //返回JSON数据、前端VUE迭代即可
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
}
