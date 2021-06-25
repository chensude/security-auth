package org.etocrm.auth.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.etocrm.auth.exception.RefuseException;

import org.etocrm.auth.uitl.Md5Utils;
import org.etocrm.database.enums.ResponseEnum;
import org.etocrm.database.util.RedisUtil;
import org.etocrm.database.util.ResponseVO;
import org.etocrm.database.util.SysUserRedisVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "auth平台鉴权接口", tags = "auth平台鉴权")
@Slf4j
public class PermissionController {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${nepous.security.oauth2.clients[0].clientId}")
    private String clientId;
    //@ApiOperation("鉴权")
    @GetMapping("/permission")
    public ResponseVO permission(Authentication authentication) {

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String res =  authentication.getName()+":" + Md5Utils.encode("Bearer "+details);

        SysUserRedisVO   redisVO=redisUtil.get(res, SysUserRedisVO.class);
        if(redisVO==null&&!authentication.getName().contains(clientId)) {
           throw new RefuseException(ResponseEnum.FAILD.getCode(),"token失效");
        }
        return ResponseVO.success(res);
    }


    @GetMapping("/token")
    public SysUserRedisVO getToken(Authentication authentication) {

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String res =  authentication.getName()+":" + Md5Utils.encode("Bearer "+details);
        return redisUtil.get(res,SysUserRedisVO.class);
    }


}
