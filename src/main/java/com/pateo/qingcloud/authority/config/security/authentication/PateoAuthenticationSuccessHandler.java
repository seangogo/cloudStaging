/**
 * 
 */
package com.pateo.qingcloud.authority.config.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pateo.qingcloud.authority.config.security.properties.LoginResponseType;
import com.pateo.qingcloud.authority.config.security.properties.SecurityProperties;
import com.pateo.qingcloud.authority.domain.rbac.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器环境下登录成功的处理器
 * 
 * @author zhailiang
 */
@Component("pateoAuthenticationSuccessHandler")
@Slf4j
public class PateoAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


	@Autowired
	private ObjectMapper objectMapper;


	@Autowired
	private SecurityProperties securityProperties;

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		log.info("登录成功");
 			if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
			response.setContentType("application/json;charset=UTF-8");
			Account account=(Account)authentication.getPrincipal();
			Map<String,String> result=new HashMap(2);
			result.put("status","ok");
			result.put("type","account");
			response.getWriter().write(objectMapper.writeValueAsString(result));
		} else {
			// 如果设置了imooc.security.browser.singInSuccessUrl，总是跳到设置的地址上
			// 如果没设置，则尝试跳转到登录之前访问的地址上，如果登录前访问地址为空，则跳到网站根路径上
			if (StringUtils.isNotBlank(securityProperties.getBrowser().getSingInSuccessUrl())) {
				requestCache.removeRequest(request, response);
				setAlwaysUseDefaultTargetUrl(true);

				setDefaultTargetUrl(securityProperties.getBrowser().getSingInSuccessUrl());
			}
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
