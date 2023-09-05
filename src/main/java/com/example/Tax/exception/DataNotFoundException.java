package com.example.Tax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity Not Found")
public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
