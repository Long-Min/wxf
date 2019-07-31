package com.chenlong.entity.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import com.chenlong.entity.base.BaseDto;

import java.util.Date;

@TableName("admin_role")
public class AdminRole extends BaseDto {
  private String name;
  private String description;
  private Date createTime;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
