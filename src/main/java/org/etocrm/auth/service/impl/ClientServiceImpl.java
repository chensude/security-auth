package org.etocrm.auth.service.impl;

import org.etocrm.auth.feign.UserFeignClient;
import org.etocrm.auth.service.ClientAuthService;
import org.etocrm.database.enums.ResponseEnum;
import org.etocrm.database.util.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientAuthService {

    @Autowired
    private UserFeignClient userFeignClient;
    @Override
    public String getIgnoreUrl(String uid) {
        ResponseVO<String> data = userFeignClient.getClientAuthUrl(uid);
        if(data!=null&&data.getCode().equals(ResponseEnum.SUCCESS.getCode())) {
            String res = data.getData();
            return res;
        }
        return null;
    }
}
