package com.itkang.blog.mapper;

import com.itkang.blog.entity.Type;
import org.springframework.stereotype.Repository;

/**
 * Created by mis on 2018/12/18.
 */
@Repository
public interface TypeMapper {
    Type findTypeById(Integer id);
}
