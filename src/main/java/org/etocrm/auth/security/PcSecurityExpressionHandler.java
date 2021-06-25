package org.etocrm.auth.security;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import org.etocrm.database.enums.ResponseEnum;

import org.etocrm.database.util.ResponseVO;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order
public class  PcSecurityExpressionHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSONUtil.toJsonStr(new JSONObject(ResponseVO.error(ResponseEnum.INSUFFICIENT_PERMISSIONS))));
	}
}