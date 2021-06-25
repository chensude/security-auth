package org.etocrm.auth.properties;

import lombok.Data;

@Data
public class OAuth2Properties {

    /**
     * 使用jwt时签名的密钥
     *
     */
    private String jwtSigningKey ="nepous";

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
