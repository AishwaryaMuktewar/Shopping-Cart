package com.shoppingcart.exception;

/**
 * 
 * Error object/Exception response that contains the error code and error message.
 *
 */
public class ExceptionResponse {
	
	private int code;
	private String messgae;
	
	public ExceptionResponse(int code, String messgae) {
		super();
		this.code = code;
		this.messgae = messgae;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
	
}
