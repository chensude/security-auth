package org.etocrm.auth.service.impl;


import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import org.etocrm.auth.security.SecurityUtils;

import org.etocrm.auth.service.UacPermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Slf4j
@Component("permissionService")
public class UacPermissionServiceImpl implements UacPermissionService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(Authentication authentication, HttpServletRequest request) {
        String currentLoginName = SecurityUtils.getCurrentLoginName();

        Set<String> currentAuthorityUrl = SecurityUtils.getCurrentAuthorityUrl();
        String requestURI = request.getRequestURI();

        String url = request.getParameter("url");
        if(url!=null) {
            requestURI=url;
        }
        log.info("验证权限loginName={}, requestURI={}, hasAuthorityUrl={}", currentLoginName, requestURI, Joiner.on(",").join(currentAuthorityUrl));

        // 超级管理员 全部都可以访问
//        if (currentLoginName.contains("admin")) {
//            return true;
//        }
//        SysUserRedisVO redisVO = redisUtil.get(resKey, SysUserRedisVO.class);
//        if (redisVO!=null&&redisVO.getAdminFlag()){
//            return true;
//        }
//
//       for(final String authority : currentAuthorityUrl) {
//            // Uac项目放过查询权限
////            if (requestURI.contains("query") || requestURI.contains("get") || requestURI.contains("check") || requestURI.contains("select")) {
////                return true;
////            }
//            if (antPathMatcher.match(authority,requestURI)||antPathMatcher.match(authority,requestURI.substring(1))) {
//
//                return true;
//            }
//        }
        if(currentLoginName.equals("anonymousUser")) {
            return false;
        }else {
            return true;
        }
    }

}
