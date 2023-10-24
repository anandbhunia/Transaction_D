package com.transaction.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponseDto {

	private final String message;
	private final Throwable throwable;
	private final HttpStatus status;

	public ExceptionResponseDto(String message, Throwable throwable, HttpStatus status) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
