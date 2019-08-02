package com.chenlong.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenlong.common.http.Result;
import com.chenlong.entity.dto.Goods;
import com.chenlong.entity.dto.GoodsSku;
import com.chenlong.entity.dto.GoodsType;
import com.chenlong.entity.dto.MerchantUser;
import com.chenlong.service.Impl.GoodsService;
import com.chenlong.service.Impl.GoodsSkuService;
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
    GoodsService goodsService;

    @Autowired
    GoodsTypeService goodsTypeService;

    @Autowired
    GoodsSkuService goodsSkuService;

    @RequestMapping("list")
    public ModelAndView toList(MerchantUser merchantUser){
        ModelAndView modelAndView = new ModelAndView("goods/list");
        merchantUser.getId();
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        List<Goods> list = goodsService.list(wrapper);
        modelAndView.addObject("goodsList",list);
        return modelAndView;
    }

    @RequestMapping("del")
    public Result<?> delGoods(String id){
        goodsService.delGoods(id);
        return Result.success();
    }

    @RequestMapping("add")
    public ModelAndView addAndEdit(@RequestParam(value = "id",defaultValue = "0",required = false)String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods/add");
        if("0".equals(id)){
            return modelAndView;
        }
        QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
        goodsQueryWrapper.lambda().eq(Goods::getId,id);
        Goods goods = goodsService.getOne(goodsQueryWrapper);
        modelAndView.addObject("goods",goods);

        List<GoodsType> goodsTypeList = goodsTypeService.list();
        modelAndView.addObject("goodsTypeList",goodsTypeList);

        QueryWrapper<GoodsSku> goodsSkuQueryWrapper = new QueryWrapper<>();
        goodsSkuQueryWrapper.lambda().eq(GoodsSku::getGoodId,id);
        List<GoodsSku> goodsSkulist = goodsSkuService.list(goodsSkuQueryWrapper);
        modelAndView.addObject("goodsSkulist",goodsSkulist);

        return modelAndView;
    }

    @RequestMapping("upload")
    public Result<?> upload(){



        return Result.success();
    }

    @RequestMapping("save")
    @ResponseBody
    public Result<?> saveGoods(){


        return Result.success();
    }

}
