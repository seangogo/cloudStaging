/**
 * 
 */
package com.pateo.qingcloud.authority.support;

import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sean
 * 2017/11/2.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * 数据异常  主键不存在 主键已存在 等等
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(DBException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Map<String, Object> DBException(DBException ex) {
		Map<String, Object> result = new HashMap<>(2);
		result.put("code",ex.getCode());
		result.put("message", ex.getMessage());
		return result;
	}

	@ExceptionHandler(ValidateException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Map<String, Object> ValidException(ValidateException ex) {
		Map<String, Object> result = new HashMap<>(2);
		result.put("code", "422");
		result.put("message",ex.getMessage() );
		return result;
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> AccessDeniedException(AccessDeniedException ex) {
		Map<String, Object> result = new HashMap(2);
		result.put("code", HttpStatus.FORBIDDEN);
		result.put("message",ex.getMessage() );
		return result;
	}





}
