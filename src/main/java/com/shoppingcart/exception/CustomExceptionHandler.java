package com.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * 
 * Global exception. 
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * 
	 * @param ex
	 * @param webRequest
	 * @return
	 * 
	 * Throw this Exception if no items found in the Cart.
	 */
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleIOException(DataNotFoundException ex, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
