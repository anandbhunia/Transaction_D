package com.transaction.exception;

public class ResourceNotFoundException extends RuntimeException {

	private String message;

	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
