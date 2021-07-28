package com.demo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author ZhangShaowei on 2021/7/26 10:45
 */
@Component("authorizer")
public class UserRealm extends AuthorizingRealm {

    private static final String USERNAME = "admin";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
//        authorizationInfo.setRoles(userInfo.getRoles());
        authorizationInfo.addStringPermission("*");
        return authorizationInfo;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        if (!USERNAME.equals(username)) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(USERNAME);
        userInfo.setPassword("111111");
        HashSet<String> set = new HashSet<>();
        set.add("admin");
        userInfo.setRoles(set);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                getName()
        );
        return authenticationInfo;
    }
}
