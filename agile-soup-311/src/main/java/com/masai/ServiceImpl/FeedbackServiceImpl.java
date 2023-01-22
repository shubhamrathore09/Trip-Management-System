package com.masai.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.Exception.FeedbackException;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.FeedbackRepository;
import com.masai.Service.FeedbackService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Customer;
import com.masai.model.Feedback;



@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	
	@Autowired
	private CustomerRepo customerRepo;
	

	@Override
	public Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException {


		Optional<CurrentLoginSession> currentUserOptional = currentSessionRepo.findByAuthkey(authKey);
		
		if(!currentUserOptional.isPresent())
		{
			throw new FeedbackException("Kindly login first into your account");
		}
		
		CurrentLoginSession currentUser = currentUserOptional.get();
		
		Customer customer = customerRepo.findById(currentUser.getSessionId()).get();
		
		if(customer==null)
		{
			throw new FeedbackException("Only customers can give feedback");
		}
		
		feedback.setCustomer(customer);
	
		
		return feedbackRepo.save(feedback);
	}

	@Override
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException {


		Optional<Feedback> optFeedback = feedbackRepo.findById(feedbackId);
		
		if(!optFeedback.isPresent())
		{
			throw new FeedbackException("No Feedback present with the given Feedback Id");
		}
		
		return optFeedback.get();
	}

	@Override
	public List<Feedback> findByCustomerId(Integer customerId, String authKey) throws FeedbackException {
		
		Optional<CurrentLoginSession> currentUserOptional = currentSessionRepo.findByAuthkey(authKey);
		
		if(!currentUserOptional.isPresent())
		{
			throw new FeedbackException("Kindly login first into your account");
		}
		
		CurrentLoginSession currentLoginSession = currentUserOptional.get();
		
		Customer customer = customerRepo.findById(currentLoginSession.getSessionId()).get();
		
		if(customer!=null)
		{
			throw new FeedbackException("Only admins can access this feature");
		}
		
		Optional<Customer> userOptional = customerRepo.findByCustomerId(customerId);
		
		if(!userOptional.isPresent())
		{
			throw new FeedbackException("No user present with the given customer Id");
		}
		
		Customer userRequired = userOptional.get();
		
		List<Feedback> list = userRequired.getFeedbacks();
		
		if(list.size()==0)
		{
			throw new FeedbackException("No feedbacks made by the customer");
		}
		
		return list;
	}

	@Override
	public List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException {
		
		 Optional<CurrentLoginSession> currentUserOptional = currentSessionRepo.findByAuthkey(authKey);
			
			if(!currentUserOptional.isPresent())
			{
				throw new FeedbackException("Kindly login first into your account");
			}
			
			CurrentLoginSession currentUserLoginSession=currentUserOptional.get();
			
			Customer customer = customerRepo.findById(currentUserLoginSession.getSessionId()).get();
			
			if(customer!=null)
			{
				throw new FeedbackException("Only admins can access this feature");
			}
			
			List<Feedback> feedbackList = feedbackRepo.getAllFeedbacks();
			
			return feedbackList;
			
			
	}

}
