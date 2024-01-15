package com.authservice.service.exception;

public class ForbiddenRuntimeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForbiddenRuntimeException() {
		super("Access is required");
	}
	
	public ForbiddenRuntimeException(String message) {
		super(message);
	}
	

}
