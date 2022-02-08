package com.cristianobalz.business.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1012610205663400637L;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}	
	
}
