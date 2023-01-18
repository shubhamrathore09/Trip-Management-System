package com.masai.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyError> LoginExceptionHandler(LoginException msg,WebRequest request){
		return null;
	}
	
}
