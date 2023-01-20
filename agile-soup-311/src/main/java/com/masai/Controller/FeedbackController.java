package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.FeedbackException;
import com.masai.Service.FeedbackService;
import com.masai.model.Feedback;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/add/{key}")
	public ResponseEntity<Feedback> addNewFeedback(@Valid @RequestBody  Feedback feedback,@PathVariable("key") String key)throws FeedbackException{
		
		
		return new ResponseEntity<>(feedbackService.addFeedback(feedback,key),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/find/{feedbackId}")
	public ResponseEntity<Feedback> findByFeedbackId(@PathVariable("feedbackId") Integer feedbackId)throws FeedbackException
	{
		
		
		return new ResponseEntity<Feedback>(feedbackService.findByFeedbackId(feedbackId),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/find/{key}/{customerId}")
	public ResponseEntity<List<Feedback>> findByCustomerId(@PathVariable("customerId") Integer customerId,@PathVariable("key") String key) throws FeedbackException
	{
		
		return new ResponseEntity<List<Feedback>>(feedbackService.findByCustomerId(customerId, key),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllFeedback/{key}")
	public ResponseEntity<List<Feedback>> viewAllFeedbacks(@PathVariable("key") String key) throws FeedbackException
	{
		
		return new ResponseEntity<List<Feedback>>(feedbackService.viewAllFeedbacks(key),HttpStatus.ACCEPTED);
	}

	

}