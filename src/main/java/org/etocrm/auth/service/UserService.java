package org.etocrm.auth.service;

import org.etocrm.auth.dto.LoginUser;
import org.etocrm.auth.dto.ac.SysUser;
import org.etocrm.auth.dto.UserPermission;
import org.etocrm.database.util.ResponseVO;

import java.util.List;

public interface UserService {
    ResponseVO<SysUser> detailByUserName(LoginUser user);

    List<UserPermission> getGrantedAuthorities(String userId, String token);
}
