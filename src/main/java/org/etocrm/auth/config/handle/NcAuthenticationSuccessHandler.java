package org.etocrm.auth.config.handle;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.common.model.RestResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.collections.MapUtils;
import org.etocrm.auth.dto.TokenValue;
import org.etocrm.auth.exception.RefuseException;
import org.etocrm.auth.uitl.DecodeHeaderUtil;
import org.etocrm.database.enums.ResponseEnum;
import org.etocrm.database.util.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("ncAuthenticationSuccessHandler")
public class NcAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${nepous.security.oauth2.clients[0].clientId}")
    private String clientId;

    @Autowired
    private ClientDetailsService clientDetailsService;
   // private static final String BEARER_TOKEN_TYPE = "Basic ";


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        logger.info("登录成功");
//        //认证服务器发放令牌
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

//        if(header==null||!header.startsWith(BEARER_TOKEN_TYPE)) {
//            throw new RefuseException(ResponseEnum.FAILD.getCode(),"请求头中无client信息");
//        }
     //   String[] tokens = DecodeHeaderUtil.extractAndDecodeHeader(header);
     //   assert tokens.length == 2;


        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

//        if (clientDetails == null) {
//            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
//        } else if (!passwordEncoder.matches(clientSecret,clientDetails.getClientSecret())) {
//            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
//        }

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        logger.info(authentication.getPrincipal());

        response.setContentType("application/json;charset=UTF-8");
        TokenValue tokenValue = new TokenValue();
        BeanUtils.copyProperties(token,tokenValue);
        tokenValue.setAccessToken("Bearer "+token.getValue());
        response.getWriter().write((objectMapper.writeValueAsString(tokenValue)));
    }

}
