package com.itkang.blog.api;

import com.itkang.blog.common.Result;
import com.itkang.blog.param.BlogParam;
import com.itkang.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by mis on 2018/12/18.
 */
@Api(tags = "博客相关")
@RestController
@RequestMapping("/blog")
public class BlogApi {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "获取博客列表", nickname = "likang")
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public Result list(@RequestBody BlogParam blogParam) {
        return blogService.findBlogListByParams(blogParam);
    }

    @ApiOperation(value = "删除博客", nickname = "likang")
    @RequestMapping(value = "/deleteBlog",method = RequestMethod.POST)
    public Result deleteBlog(@RequestBody BlogParam blogParam) {
        return blogService.deleteById(blogParam);
    }
}
