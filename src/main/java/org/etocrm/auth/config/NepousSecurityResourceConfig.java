package org.etocrm.auth.config;

import com.alibaba.fastjson.JSONObject;
import org.etocrm.auth.config.filter.MyUsernamePasswordAuthenticationFilter;
import org.etocrm.auth.security.AuthorizeConfigManager;
import org.etocrm.auth.uitl.GetRequestJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableResourceServer
public class NepousSecurityResourceConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private OAuth2WebSecurityExpressionHandler ncSecurityExpressionHandler;
    @Autowired
    private AuthenticationFailureHandler ncAuthenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler ncAuthenticationSuccessHandler;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        formAuthenticationConfig.configure(http);

        http.headers().and().csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());

    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(ncSecurityExpressionHandler);
    }

    @Bean
    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(ncAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(ncAuthenticationFailureHandler);
        return filter;
    }
}
