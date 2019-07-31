package com.chenlong.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chenlong.entity.base.BaseDto;

@TableName("goods_type")
public class GoodsType extends BaseDto {

  private String name;
  private Long orderNum;



  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Long getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(Long orderNum) {
    this.orderNum = orderNum;
  }

}
