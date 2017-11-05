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
public class OAuth2Properties {
	
	/**
	 * 使用jwt时为token签名的秘钥
	 */
	private String jwtSigningKey = "imooc";
	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};

	
}
