package com.chenlong.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chenlong.entity.base.BaseDto;

@TableName("goods")
public class Goods extends BaseDto {

    private String name;
    private Long merchantUserId;
    private Long goodsTypeId;
    private String pic;
    private String promoteDesc;
    private String skuTitle;
    private String skuCost;
    private String skuPrice;
    private String skuPmoney;
    private Long orderNum;
    private Long state;
    private java.sql.Timestamp createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(Long merchantUserId) {
        this.merchantUserId = merchantUserId;
    }


    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public String getPromoteDesc() {
        return promoteDesc;
    }

    public void setPromoteDesc(String promoteDesc) {
        this.promoteDesc = promoteDesc;
    }


    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }


    public String getSkuCost() {
        return skuCost;
    }

    public void setSkuCost(String skuCost) {
        this.skuCost = skuCost;
    }


    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }


    public String getSkuPmoney() {
        return skuPmoney;
    }

    public void setSkuPmoney(String skuPmoney) {
        this.skuPmoney = skuPmoney;
    }


    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }


    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public enum State{
        PENDING(0L,"待审核"),
        UP(1L,"已上架"),
        DOWN(2L,"已下架");

        private Long code;
        private String description;

        State(Long code, String description){
            this.code=code;
            this.description = description;
        }

        public Long getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public State get(Long code){
            for (State state : State.values()){
                if(state.getCode().equals(code)){
                    return state;
                }
            }
            return null;
        }

        public State get(String description){
            for (State state : State.values()){
                if(state.getDescription().equals(description)){
                    return state;
                }
            }
            return null;
        }

    }
}
