package com.chenlong.entity.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import com.chenlong.entity.base.BaseDto;

@TableName("goods_sku")
public class GoodsSku extends BaseDto {

    private Long goodId;
    private String title;
    private String cost;
    private String price;
    private String pmoney;
    private Long orderNo;


    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getPmoney() {
        return pmoney;
    }

    public void setPmoney(String pmoney) {
        this.pmoney = pmoney;
    }


    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

}
