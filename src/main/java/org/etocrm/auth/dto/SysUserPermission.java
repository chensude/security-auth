package org.etocrm.auth.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "用户权限")
public interface  SysUserPermission {

    Long getId();

    Long getOrgId();

    String getMenuName();

    String getMenuRoute();

}