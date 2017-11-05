/**
 * 
 */
package com.pateo.qingcloud.authority.config.security.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sean
 *
 */
@Getter
@Setter
public class SmsCodeProperties {
	
	/**
	 * 验证码长度
	 */
	private int length = 6;
	/**
	 * 过期时间
	 */
	private int expireIn = 60;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;
}
