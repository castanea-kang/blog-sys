package com.itkang.blog.mapper;

import com.itkang.blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mis on 2018/12/18.
 */
@Repository
public interface BlogMapper {

    List<Blog> findBlogListByParams(Map<String,Object> params);

    Integer deleteBlogById(Integer id);
}
