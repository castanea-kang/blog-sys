package com.itkang.blog.Controller;

import com.itkang.blog.common.Result;
import com.itkang.blog.entity.User;
import com.itkang.blog.service.UserService;
import com.itkang.blog.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Map;

/**
 * Created by mis on 2018/12/13.
 */
@Api(tags ="后台登陆")
@Controller
//@RequestMapping(value = "blog")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value="登陆首页")
    @RequestMapping(value="index",method= RequestMethod.GET)
    public String   index() {
        logger.info("登陆首页");
        return "web/index";
    }
    @ApiOperation(value="登陆")
    @RequestMapping(value="login",method=RequestMethod.POST)
    public @ResponseBody
    String login(HttpServletRequest request, HttpServletResponse response,
                 String account, String password) throws Exception {
        logger.info("登陆");
        String param = "false";
        if(account!=null || !account.isEmpty() || password!=null || !password.isEmpty()){
            User user = userService.findByUsernameAndPassword(account,password);
            if(user != null){
                param =  "true";
            }
        }
        return param;
    }

    @ApiOperation(value="后台展示")
    @RequestMapping(value="main",method=RequestMethod.GET)
    public String main(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        model.addAttribute("ip", hostAddress);
        model.addAttribute("address", "北京");
        model.addAttribute("time", DateUtil.getTime());
        return "web/main";
    }
    @ApiOperation(value="博客列表")
    @RequestMapping(value="blog-list")
    public String blogList(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
        return "web/blog-list";
    }

    @ApiOperation(value="测试展示")
    @RequestMapping(value="face",method=RequestMethod.GET)
    public String face(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
        return "face/index";
    }

}
