package org.etocrm.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationConfig {

    @Autowired
    private AuthenticationFailureHandler ncAuthenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler ncAuthenticationSuccessHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .failureHandler(ncAuthenticationFailureHandler)
                .successHandler(ncAuthenticationSuccessHandler);
    }
}
