package org.etocrm.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPermission implements Serializable {

    private static final long serialVersionUID = -5578331347646376080L;
    private Long id;

    private String menuRoute;

    private String menuName;

    private Long orgId;
}
