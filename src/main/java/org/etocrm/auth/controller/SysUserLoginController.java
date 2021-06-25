package org.etocrm.auth.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.etocrm.auth.dto.LoginUser;
import org.etocrm.auth.dto.TokenValue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


@RestController
@Api(value = "中台用户登录", tags = "中台用户登录")
public class SysUserLoginController {

    private static final int TOKEN_EXPIRATION_BUFFER_SECONDS = 15;



//    @ApiOperation("中台用户登录")
//    @PostMapping("/login")
//    public TokenValue login(@RequestBody LoginUser user) {
//      //  TokenValue login = authenticationService.login(user.getUserName(), user.getPassword());
//     //   login.setAccessToken("Bearer "+login.getAccessToken());
//        return null;
//    }

    @GetMapping("/logout")
    public String login(HttpServletRequest request) throws ServletException {
        request.logout();
        return "注销成功";
    }

    @PostMapping(value = "/refresh/token")
    public ResponseEntity<TokenValue> refresh(String refreshToken) {

       // return new ResponseEntity<>(authenticationService.refresh(refreshToken), HttpStatus.OK);
        return null;
    }

    private boolean hasTokenExpired(final OAuth2AccessToken accessToken) {
        if (Objects.isNull(accessToken.getExpiresAt())) {
            return true;
        }

        return accessToken.getExpiresAt().isBefore(Instant.now().minus(TOKEN_EXPIRATION_BUFFER_SECONDS, ChronoUnit.SECONDS));
    }
}
