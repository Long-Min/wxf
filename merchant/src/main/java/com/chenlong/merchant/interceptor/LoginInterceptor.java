package com.chenlong.merchant.interceptor;

import com.chenlong.entity.dto.Admin;
import com.chenlong.entity.dto.MerchantUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//SpringBoot拦截器,拦截可能为空的admin,也需要再webconfig中配置
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws  Exception{
        Subject subject = SecurityUtils.getSubject();
        MerchantUser merchantUser = (MerchantUser) subject.getPrincipal();

        if(merchantUser == null){

            request.getRequestDispatcher("/").forward(request,response);
        }
        request.setAttribute("CURRENT_ADMIN",merchantUser);
        return super.preHandle(request,response,handler);
    }
}
