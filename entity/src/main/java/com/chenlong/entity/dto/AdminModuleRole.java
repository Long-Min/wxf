package com.chenlong.entity.dto;


public class AdminModuleRole {

  private Long id;
  private Long adminModuleId;
  private Long adminRoleId;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getAdminModuleId() {
    return adminModuleId;
  }

  public void setAdminModuleId(Long adminModuleId) {
    this.adminModuleId = adminModuleId;
  }


  public Long getAdminRoleId() {
    return adminRoleId;
  }

  public void setAdminRoleId(Long adminRoleId) {
    this.adminRoleId = adminRoleId;
  }

}
