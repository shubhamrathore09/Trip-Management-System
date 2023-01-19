package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyError> LoginExceptionHandler(LoginException msg,WebRequest request){
		MyError myError=new MyError(msg.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<>(myError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError>ValidationError(MethodArgumentNotValidException msg){
		MyError myError=new MyError(msg.getBindingResult().getFieldError().getDefaultMessage(), "validation exception", LocalDateTime.now());
		return new ResponseEntity<MyError>(myError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError>NoHandler(NoHandlerFoundException ex,WebRequest request){
		MyError myError=new MyError(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyError>(myError,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError>AllTypeOfException(Exception msg,WebRequest request){
		MyError myError=new MyError(msg.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyError>(myError,HttpStatus.BAD_GATEWAY);
	}
	
	
	
}
