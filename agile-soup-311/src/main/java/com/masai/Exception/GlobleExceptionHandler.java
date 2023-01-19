package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
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
	
	@ExceptionHandler(BookingException.class)
	public ResponseEntity<MyError> bookingExceptionHandler(BookingException msg, WebRequest request) {
		MyError error = new MyError();
		error.setDateTime(LocalDateTime.now());
		error.setMsg(msg.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PackageException.class)
	public ResponseEntity<MyError> packagingExceptionHandler(PackageException msg, WebRequest request) {
		MyError error = new MyError();
		error.setDateTime(LocalDateTime.now());
		error.setMsg(msg.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentDetailException.class)
	public ResponseEntity<MyError> paymentDetailExceptionHandler(PaymentDetailException msg, WebRequest request) {
		MyError error = new MyError();
		error.setDateTime(LocalDateTime.now());
		error.setMsg(msg.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HotelException.class)
	public ResponseEntity<MyError> hotelExceptionHandler(HotelException msg, WebRequest request) {
		MyError error = new MyError();
		error.setDateTime(LocalDateTime.now());
		error.setMsg(msg.getMessage());
		error.setDetails(request.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<MyError> OtherExceptionHandler(FeedbackException msg, WebRequest request){
		MyError error  = new MyError();
		error.setDateTime(LocalDateTime.now());
		error.setMsg(msg.getMessage());
		error.setDetails(request.getDescription(false));
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_REQUEST);
	}
}
