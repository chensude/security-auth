package org.etocrm.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class SysUserPermissionDTO  {

    private Long orgId;

    List<UserPermission> permissions;
}