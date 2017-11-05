/**
 * 
 */
package com.pateo.qingcloud.authority.config.security;

import com.pateo.qingcloud.authority.config.security.authentication.FormAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.formLogin()
				 .loginPage("/signIn.html")
				 .loginProcessingUrl("/authentication/form")
				 .and()
				 .authorizeRequests()
				 .antMatchers("/signIn.html").permitAll()
				 .antMatchers("/swagger-ui.html").permitAll()
				 .anyRequest()
				 .authenticated()
		         .and().csrf().disable();
		
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

}
