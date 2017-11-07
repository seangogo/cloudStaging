package com.pateo.qingcloud.authority.exception;


import com.pateo.qingcloud.authority.menu.ResultEnum;

/**
 * @author sean
 * 2017/11/2.
 */
public class DBException extends RuntimeException {

	private static final long serialVersionUID = -6112780192479692859L;
	
	private String code;

	private String message;

	public DBException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
