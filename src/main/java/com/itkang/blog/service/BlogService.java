package com.itkang.blog.service;

import com.itkang.blog.common.Result;
import com.itkang.blog.param.BlogParam;

/**
 * Created by mis on 2018/12/18.
 */
public interface BlogService {
    Result findBlogListByParams(BlogParam blogParam);
    Result deleteById(BlogParam blogParam);
}
