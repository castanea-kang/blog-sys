package com.itkang.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itkang.blog.common.Result;
import com.itkang.blog.entity.Blog;
import com.itkang.blog.entity.Type;
import com.itkang.blog.entity.User;
import com.itkang.blog.mapper.BlogMapper;
import com.itkang.blog.model.BlogModel;
import com.itkang.blog.param.BlogParam;
import com.itkang.blog.service.BlogService;
import com.itkang.blog.service.TypeService;
import com.itkang.blog.service.UserService;
import com.itkang.blog.utils.DateUtil;
import com.itkang.blog.utils.ParamCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mis on 2018/12/18.
 */
@Service
public class BlogServiceImpl implements BlogService{

    private static final BeanCopier p2m = BeanCopier.create(Blog.class, BlogModel.class, false);

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserService userService;
    @Autowired
    TypeService typeService;

    private BlogModel copy(Blog blog) {
        if (blog ==  null){
            return null;
        }
        BlogModel model = new BlogModel();
        p2m.copy(blog,model,null);
        return  model;
    }

    @Override
    public Result findBlogListByParams(BlogParam blogParam) {
        Map<String,Object> params= new HashMap<>();
        List<BlogModel> blogModelList = new ArrayList<>();
        if(!ParamCheckUtils.isBlank(blogParam.getTypeId())){
            params.put("typeId",blogParam.getTypeId());
        }
        if(!ParamCheckUtils.isBlank(blogParam.getStatus())){
            params.put("status",blogParam.getStatus());
        }
        if(!ParamCheckUtils.isBlank(blogParam.getKeyword())){
            params.put("keyword",blogParam.getKeyword());
        }
        PageHelper.startPage(blogParam.getPageNo(),blogParam.getPageSize());
        List<Blog> blogList =  blogMapper.findBlogListByParams(params);
        PageInfo pageInfo = new PageInfo(blogList);
        if(blogList.size()>0){
            for(Blog blog : blogList){
                BlogModel blogModel = copy(blog);
                User user = userService.findById(blog.getUserId());
                if(user != null){
                    blogModel.setBlogName(user.getUsername());
                }
                if(blog.getTypeId() != null){
                    Type type = typeService.findById(blog.getTypeId());
                    if(type != null){
                        blogModel.setTypeStr(type.getName());
                    }
                }
                String addtime = DateUtil.date2Str(blog.getAddTime(),"yyyy-MM-dd HH:mm:ss");
                blogModel.setAddTimeStr(addtime);
                blogModelList.add(blogModel);
            }
        }
        pageInfo.setList(blogModelList);
        return Result.success(pageInfo);
    }

    @Override
    public Result deleteById(BlogParam blogParam) {
        if(ParamCheckUtils.isBlank(blogParam.getBlogId())){
            return Result.error("请传入正确的参数！");
        }
        Integer ret = blogMapper.deleteBlogById(blogParam.getBlogId());
        System.out.println(ret);
        if(ret < 0){
            return Result.error("删除失败！");
        }
        return Result.success();
    }

}
