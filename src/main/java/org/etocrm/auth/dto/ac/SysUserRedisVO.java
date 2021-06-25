package org.etocrm.auth.dto.ac;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRedisVO implements Serializable {

    private Long userId;

    private Long orgId;
}
