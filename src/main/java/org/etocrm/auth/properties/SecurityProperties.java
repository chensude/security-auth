package org.etocrm.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "nepous.security")
public class SecurityProperties {

    /**
     * app环境配置
     *
     */
//    private AppProperties app = new AppProperties();

    /**
     * oauth2 认证服务器配置
     */
    private  OAuth2Properties oauth2 = new OAuth2Properties();
}
