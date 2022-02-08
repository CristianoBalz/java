package com.cristianobalz.dto.exception;

public class DtoException extends Exception {

	private static final long serialVersionUID = 5282132694425905965L;

	public DtoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DtoException(String message) {
		super(message);
	}

}
