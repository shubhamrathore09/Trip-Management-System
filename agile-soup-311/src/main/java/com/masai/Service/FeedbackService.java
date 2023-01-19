package com.masai.Service;

import com.masai.Exception.FeedbackException;
import com.masai.model.Feedback;

public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback) throws FeedbackException;
	

}
