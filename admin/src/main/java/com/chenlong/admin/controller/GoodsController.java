package com.chenlong.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenlong.common.http.Result;
import com.chenlong.entity.dto.Goods;
import com.chenlong.entity.dto.GoodsType;
import com.chenlong.service.Impl.GoodsService;
import com.chenlong.service.Impl.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("goods")
public class GoodsController {


    @Autowired
    GoodsTypeService goodsTypeService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("list")
    public ModelAndView goodsList(@RequestParam(value = "goodsTypeId",required = false,defaultValue = "0") String goodsTypeId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods/list");

        List<GoodsType> goodsTypeList = goodsTypeService.listAsc();
        modelAndView.addObject("goodsTypes",goodsTypeList);

        List<Goods> goodsList = goodsService.getByTypeId(goodsTypeId);
        modelAndView.addObject("goodsList",goodsList);
        return modelAndView;
    }

    @RequestMapping("up")
    @ResponseBody
    public Result<?> upGoods(@RequestParam(value = "id",required = true) String id){
        goodsService.upGoods(id);
        return Result.success();
    }

    @RequestMapping("down")
    @ResponseBody
    public Result<?> downGoods(@RequestParam(value = "id",required = true) String id){
        goodsService.downGoods(id);
        return Result.success();
    }

    @RequestMapping("del")
    @ResponseBody
    public Result<?> delGoods(@RequestParam(value = "id",required = true) String id){
        goodsService.delGoods(id);
        return Result.success();
    }



}
