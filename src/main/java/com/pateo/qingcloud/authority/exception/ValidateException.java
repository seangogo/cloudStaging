/**
 * 
 */
package com.pateo.qingcloud.authority.exception;


/**
 * @author seangogo
 */

public class ValidateException extends RuntimeException {

	private String code;


	public ValidateException(String code, String message) {
		super(message);
		this.code = code;
	}
}
