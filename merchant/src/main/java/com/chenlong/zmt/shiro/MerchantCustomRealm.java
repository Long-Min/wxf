package com.chenlong.zmt.shiro;

import com.chenlong.common.http.ResultCode;
import com.chenlong.entity.dto.MerchantUser;
import com.chenlong.service.Impl.MerchantUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MerchantCustomRealm extends AuthorizingRealm {

    @Autowired
    MerchantUserService merchantUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        MerchantUser merchantUser = merchantUserService.getUserByName(username);
        if(merchantUser == null){
            throw new RuntimeException(ResultCode.LOGIN_ERROR.getDescription());
        }
        if(!merchantUser.getPassword().equals(password)){
            throw new RuntimeException(ResultCode.LOGIN_ERROR.getDescription());
        }
        return new SimpleAuthenticationInfo(merchantUser,merchantUser.getPassword(),merchantUser.getName());
    }
}
