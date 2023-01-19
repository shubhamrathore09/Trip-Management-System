package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.FeedbackService;
import com.masai.model.Feedback;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class FeedbackController {
	
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/feedback")
	public ResponseEntity<Feedback> addNewFeedback(@RequestBody Feedback feedback){
		
		
		return new ResponseEntity<>(feedbackService.addFeedback(feedback),HttpStatus.CREATED);
		
	}
	
	

}
