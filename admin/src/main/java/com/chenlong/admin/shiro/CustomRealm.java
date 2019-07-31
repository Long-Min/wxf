package com.chenlong.admin.shiro;

import com.chenlong.common.http.ResultCode;
import com.chenlong.entity.dto.Admin;
import com.chenlong.service.Impl.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

//Realm类,Shiro管理登录权限和身份验证的功能代码,需要再ShiroConfig配置类中配置生效
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    AdminService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        HashSet<String> set = new HashSet<>();
        set.add("ADMIN");
        simpleAuthorizationInfo.setStringPermissions(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        String password = new String (token.getPassword());
        Admin admin = service.getUserByName(username);
        if(admin == null){
            throw  new RuntimeException(ResultCode.LOGIN_ERROR.getDescription());
        };
        if(!admin.getPassword().equals(password)){
            throw  new RuntimeException(ResultCode.LOGIN_ERROR.getDescription());
        }
//        return Result.create(ResultCode.LOGIN_SUCCESS);
        return new SimpleAuthenticationInfo(admin,admin.getPassword(),admin.getAccount());
    }
}
