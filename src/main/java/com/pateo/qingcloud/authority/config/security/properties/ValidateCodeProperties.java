/**
 * 
 */
package com.pateo.qingcloud.authority.config.security.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码配置
 * @author sean
 *
 */
@Setter
@Getter
public class ValidateCodeProperties {
	
	/**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();

	
}
