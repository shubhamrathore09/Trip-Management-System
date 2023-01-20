package com.masai.Service;

import java.util.List;

import com.masai.Exception.FeedbackException;
import com.masai.model.Feedback;


public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException;	
	
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException;
	
	public List<Feedback> findByCustomerId(Integer customerId,String authKey) throws FeedbackException;
	
	public List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException;

}
