package com.chenlong.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenlong.entity.dto.MerchantUser;
import com.chenlong.service.Impl.MerchantUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    MerchantUserService merchantUserService;


    @RequestMapping({"/","login"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("login.do")
    public ModelAndView login(String username, String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                return new ModelAndView("redirect:/index");
            }
        }catch (AuthenticationException e){
        }
        return new ModelAndView("redirect:/login");
    }

}
