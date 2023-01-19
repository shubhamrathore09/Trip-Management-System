package com.masai.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.FeedbackException;
import com.masai.Repository.FeedbackRepository;
import com.masai.Service.FeedbackService;
import com.masai.model.Feedback;


@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	

	@Override
	public Feedback addFeedback(Feedback feedback) throws FeedbackException {
		
		Feedback newFeedback = feedbackRepo.save(feedback);
		
		if(newFeedback == null) {
			throw new FeedbackException("Feedback not added...");
		}
		return newFeedback;
		
	}

}
