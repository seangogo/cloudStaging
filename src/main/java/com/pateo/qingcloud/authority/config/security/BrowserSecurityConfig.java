/**
 * 
 */
package com.pateo.qingcloud.authority.config.security;

import com.pateo.qingcloud.authority.config.security.authentication.FormAuthenticationConfig;
import com.pateo.qingcloud.authority.config.security.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 浏览器环境下安全配置主类
 * 
 * @author sean
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;

	@Autowired
	protected AuthenticationSuccessHandler pateoAuthenticationSuccessHandler;

	@Autowired
	protected AuthenticationFailureHandler pateoAuthenctiationFailureHandler;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http .formLogin()
				 .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				 .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
				 .failureUrl("/error/403.html")
				 .successHandler(pateoAuthenticationSuccessHandler)
				 .failureHandler(pateoAuthenctiationFailureHandler)
				 .and()
				 .authorizeRequests()
				 //既不是匿名用户又不是自动登陆的用户才可以修改密码进一步保证用户账号安全性
				 .antMatchers("/account/password/modify").fullyAuthenticated()
				 //只有管理员才有新建用户的权利
				 .antMatchers("/new-user/**", "/delete-user-*","/account/lock/**/**").access("hasRole('Sysdba_0')")
				 //绑定角色只有管理员和超级管理才可以操作
				/* .antMatchers("/role/**", "/resource/**")
				 .access("hasRole('admin_*') or hasRole('Sysdba_0')")*/
				 .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						 "/signIn.html","/error/403.html","logout"
						 ,"swagger-ui.html","/swagger-resources/**","/v2/**","/account/isUse").permitAll()
				 .antMatchers(HttpMethod.GET,
						 "/**/*.html",
						 "/admin/me",
						 "/resource").authenticated()
				 .anyRequest()
				 .access("@rbacService.hasPermission(request, authentication)")
		         .and().csrf().disable()
				 //session过期后跳转路径
				 .sessionManagement().invalidSessionUrl("/signIn.html")
				 .and()
				 //单点登录跳转路径
				 .sessionManagement().maximumSessions(1).expiredUrl("/signIn.html")
				 .and();
	}



	/**
	 * 记住我功能的token存取器配置
	 * @return
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}


	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
