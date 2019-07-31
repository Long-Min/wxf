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

}
