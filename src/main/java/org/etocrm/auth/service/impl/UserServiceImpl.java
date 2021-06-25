package org.etocrm.auth.service.impl;

import org.etocrm.auth.dto.LoginUser;
import org.etocrm.auth.dto.UserPermission;
import org.etocrm.auth.dto.ac.SysUser;
import org.etocrm.auth.feign.UserFeignClient;
import org.etocrm.auth.service.UserService;
import org.etocrm.database.enums.ResponseEnum;
import org.etocrm.database.util.ResponseVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserFeignClient userFeignClient;
    @Override
    public ResponseVO<SysUser> detailByUserName(LoginUser user) {
       return   userFeignClient.login(user);
    }

    @Override
    public List<UserPermission> getGrantedAuthorities(String uid, String token) {

        ResponseVO<List<UserPermission>> data = userFeignClient.getUserPermissions(uid,token);
        //userFeignClient.getUserPermissions(uid);
        if(data!=null&&data.getCode().equals(ResponseEnum.SUCCESS.getCode())) {
            List<UserPermission> res = data.getData();
            return res;
        }
        return null;
    }
}
