/**
 * 
 */
package com.pateo.qingcloud.authority.config.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pateo.qingcloud.authority.config.security.properties.LoginResponseType;
import com.pateo.qingcloud.authority.vo.result.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

	@Value("{pateo.security.browser.singInSuccessUrl}")
	private String getSingInSuccessUrl;
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		log.info("登录成功");
		if (LoginResponseType.JSON.equals(getSingInSuccessUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			String type = authentication.getClass().getSimpleName();
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(type)));
		} else {
			// 如果设置了imooc.security.browser.singInSuccessUrl，总是跳到设置的地址上
			// 如果没设置，则尝试跳转到登录之前访问的地址上，如果登录前访问地址为空，则跳到网站根路径上
			if (StringUtils.isNotBlank(getSingInSuccessUrl)) {
				requestCache.removeRequest(request, response);
				setAlwaysUseDefaultTargetUrl(true);
				setDefaultTargetUrl(getSingInSuccessUrl);
			}
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
