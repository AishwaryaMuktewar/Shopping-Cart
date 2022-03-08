package com.shoppingcart.exception;

public class ShoppingCartException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	
	public ShoppingCartException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
