package org.etocrm.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRedisVO implements Serializable {
    private static final long serialVersionUID = -7947562652503451196L;

    private Long userId;
    private Long orgId;

    private String uid;

    private String name;
}
