package com.itkang.blog.shiro;


import com.itkang.blog.entity.User;
import com.itkang.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;



public class ShiroDbRealm extends AuthorizingRealm {

    private static final String ALGORITHM = "MD5";

    @Resource
    private UserService userService;

    public ShiroDbRealm() {
        super();
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.findByUserName(username);
        if(userInfo == null){
            return null;
        }
        Object obj = new SimpleHash("md5",userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getUsername()),1);
        System.out.println(obj);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getUsername()),
                getName()  //realm name
        );
        return authenticationInfo;
    }


    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }


    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

}
