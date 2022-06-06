package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String id) {
		System.out.println("User not found :: "+id);
	}

}
