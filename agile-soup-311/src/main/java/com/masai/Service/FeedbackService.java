package com.masai.Service;

import java.util.List;

import com.masai.Exception.CustomerException;
import com.masai.Exception.FeedbackException;
import com.masai.Exception.LoginException;
import com.masai.model.Feedback;


public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException,LoginException,CustomerException;	
	
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException,LoginException,CustomerException;
	
	public List<Feedback> findByCustomerId(Integer customerId,String authKey) throws FeedbackException,LoginException,CustomerException;
	
	public List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException,LoginException,CustomerException;

}
