/**
 * 
 */
package com.pateo.qingcloud.authority.config.security.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 图片验证码配置项
 * 
 * @author zhailiang
 *
 */
@Setter
@Getter
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		setLength(4);
	}
	
	/**
	 * 图片宽
	 */
	private int width = 67;
	/**
	 * 图片高
	 */
	private int height = 23;


}
