package com.chenlong.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenlong.common.http.Result;
import com.chenlong.entity.dto.MerchantUser;
import com.chenlong.service.Impl.MerchantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("merchant")
public class MerchantContrller {

    @Autowired
    MerchantUserService service;

    @RequestMapping("list")
    public ModelAndView toList(@RequestParam(required = false) String query){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("merchant/list");

        QueryWrapper<MerchantUser> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(query)) {
            wrapper.lambda().like(MerchantUser::getName, query).or().like(MerchantUser::getUserName, query);
        }
        wrapper.lambda().orderByAsc(MerchantUser::getCreateTime);

        List<MerchantUser> list = service.list();
        modelAndView.addObject("merchantUserList",list);
        return modelAndView;
    }

    @PostMapping("save")
    @ResponseBody
    public Result<?> save(MerchantUser merchantUser){
        service.saveOrUpdate(merchantUser);
        return Result.success();
    }

    @PostMapping("del")
    @ResponseBody
    public Result<?> del(@RequestParam(value = "id",required = true) Long id){
        service.removeById(id);
        return Result.success();
    }

    @PostMapping("getById")
    @ResponseBody
    public Result<MerchantUser> getById(@RequestParam(value = "id", required = true) Long id) {
        return Result.success(service.getById(id));
    }

}
