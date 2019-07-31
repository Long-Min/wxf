package com.chenlong.admin.controller;

import com.chenlong.common.http.Result;
import com.chenlong.entity.dto.Admin;
import com.chenlong.entity.vo.JqueryTreeView;
import com.chenlong.service.Impl.AdminModuleService;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    AdminModuleService service;

    @RequestMapping("index")
    public ModelAndView toIndex(Admin admin){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
//        mav.addObject("name","张三");

//        Subject subject = SecurityUtils.getSubject();//shiro会有admin,结合springmvc获得admin
//        Admin admin = (Admin)subject.getPrincipal();

        mav.addObject("name",admin.getName());
        return mav;
    }

//    @RequiresPermissions("STU")
//    @RequestMapping("test.do")
//    @ResponseBody
//    public Result<?> doTest(){
//        return Result.success("是admin");
//    }


    @RequestMapping("func-list")
    @ResponseBody
    public Result<List<JqueryTreeView>> getModuleList(Admin admin) {
        return Result.success(service.getModuleByAdminId(admin.getId()));
    }
}
