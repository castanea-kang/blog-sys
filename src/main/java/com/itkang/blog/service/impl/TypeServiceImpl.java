package com.itkang.blog.service.impl;

import com.itkang.blog.entity.Type;
import com.itkang.blog.mapper.TypeMapper;
import com.itkang.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mis on 2018/12/18.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public Type findById(Integer typeId) {
        return typeMapper.findTypeById(typeId);
    }
}
