package com.iteam.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameInUseException extends RuntimeException {

	private static final long serialVersionUID = 1135813L;
	
	@Override
	public String getMessage() {
		return "The requested username is already in use.";
	}
}
