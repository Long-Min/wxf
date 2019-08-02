package com.chenlong.merchant.controller;

import com.chenlong.common.http.Result;
import com.chenlong.entity.vo.JqueryTreeView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class IndexController {

    @RequestMapping("index")
    public ModelAndView toIndex() {

        return new ModelAndView("index");
    }

    @RequestMapping("func-list")
    @ResponseBody
    public Result<?> toList() {
        ArrayList<JqueryTreeView> jqueryTreeViews = new ArrayList<>();
        JqueryTreeView treeView1 = new JqueryTreeView();
        treeView1.setFid(1);
        treeView1.setText("商品管理");
        treeView1.setHref("goods/list");
        JqueryTreeView treeView2 = new JqueryTreeView();
        treeView2.setFid(2);
        treeView2.setText("订单管理");
        treeView2.setHref("order/list");
        jqueryTreeViews.add(treeView1);
        jqueryTreeViews.add(treeView2);
        return Result.success(jqueryTreeViews);
    }
}
