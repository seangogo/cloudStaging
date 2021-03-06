/**
 * 
 */
package com.pateo.qingcloud.authority.config.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pateo.qingcloud.authority.config.security.properties.LoginResponseType;
import com.pateo.qingcloud.authority.config.security.properties.SecurityProperties;
import com.pateo.qingcloud.authority.vo.result.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 浏览器环境下登录失败的处理器
 * 
 * @author zhailiang
 *
 */
@Component("pateoAuthenctiationFailureHandler")
public class PateoAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;

	@Value("${pateo.security.browser.signInResponseType}")
	private String signInResponseType;
	@Autowired
	private SecurityProperties securityProperties;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败:{}",exception.getMessage());
		logger.info(LoginResponseType.JSON.name());
		if (LoginResponseType.JSON.equals(securityProperties.getBrowser())) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		}else{
			setDefaultFailureUrl("/error/402.html");
			super.onAuthenticationFailure(request, response, exception);
		}

	}
}
