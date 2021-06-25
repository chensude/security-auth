package org.etocrm.auth.feign;

import org.etocrm.auth.config.FeignConfiguration;
import org.etocrm.auth.dto.LoginUser;
import org.etocrm.auth.dto.UserPermission;
import org.etocrm.auth.dto.ac.SysUser;
import org.etocrm.database.util.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "etocrm-uam",configuration = FeignConfiguration.class)
public interface UserFeignClient {

     @PostMapping("/sysUser/login")
     ResponseVO<SysUser> login(@RequestBody LoginUser user);


     @GetMapping("/sysUser/permissions")
     ResponseVO<List<UserPermission>> getUserPermissions(@RequestParam("uid") String uid, @RequestParam("token") String token);

     @GetMapping("/clientAuthUrl")
     ResponseVO<String> getClientAuthUrl(@RequestParam("uid") String uid);

}
