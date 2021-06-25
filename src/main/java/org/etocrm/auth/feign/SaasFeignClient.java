package org.etocrm.auth.feign;

import org.etocrm.auth.config.FeignConfiguration;
import org.etocrm.database.util.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "etocrm-saas",configuration = FeignConfiguration.class)
public interface SaasFeignClient {

     @GetMapping("/user/role")
     ResponseVO<String> getUserRole(@RequestParam String uid);

}
