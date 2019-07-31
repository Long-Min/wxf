package com.chenlong.admin.controller;


import com.chenlong.common.http.Result;
import com.chenlong.common.http.ResultCode;
import com.chenlong.entity.dto.Admin;
import com.chenlong.service.Impl.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {


    @Autowired
    AdminService service;

    @RequestMapping({"/","login"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login.do")
    //@ResponseBody
    public /*Result<?>*/ String doLogin(Model model, @RequestParam("username") String username,
                                        @RequestParam("password")String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                //return Result.create(ResultCode.LOGIN_SUCCESS);
                return "forward:/index";
            }
        }catch (AuthenticationException e){
        }
        model.addAttribute("error",ResultCode.LOGIN_ERROR.getDescription());
        return "forward:/";
        //return Result.create(ResultCode.LOGIN_ERROR);

//        Admin admin= service.getUserByName(username);
//
//        Result<String> result = new Result();
//        if(admin == null){
//            return Result.create(ResultCode.LOGIN_ERROR);
//        };
//        if(!admin.getPassword().equals(password)){
//            return Result.create(ResultCode.LOGIN_ERROR);
//        }
//        return Result.create(ResultCode.LOGIN_SUCCESS);
    }
}
