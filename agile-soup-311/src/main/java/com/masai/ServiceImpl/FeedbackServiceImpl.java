package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.Exception.FeedbackException;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.FeedbackRepository;
import com.masai.Service.FeedbackService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Feedback;
import com.masai.model.User;


@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	//private UserRepo userRepo;
	
	


	@Override
	public Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException {


		Optional<CurrentLoginSession> currentUserOptional = currentSessionRepo.findByAuthkey(authKey);
		
		if(!currentUserOptional.isPresent())
		{
			throw new FeedbackException("Kindly login first into your account");
		}
		
		CurrentLoginSession currentUser = currentUserOptional.get();
		
		User user = userRepo.findById(currentLoginSession.getUserId()).get();
		
		if(user.getUserType().equals("admin"))
		{
			throw new FeedbackException("Only users can give feedback");
		}
		
		
		feedback.setUser(user);
		
		return feedbackRepo.save(feedback);
	}

	@Override
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException {


		Optional<Feedback> optFeedback= feedbackRepo.findById(feedbackId);
		
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
		
		User user = userRepo.findById(currentLoginSession.getUserId()).get();
		
		if(user.getUserType().equals("User"))
		{
			throw new FeedbackException("Only admins can access this feature");
		}
		
		Optional<User> userOptional = userRepo.findByUserId(customerId);
		
		if(!userOptional.isPresent())
		{
			throw new FeedbackException("No user present with the given customer Id");
		}
		
		User userRequired=userOptional.get();
		
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
			
			User user = userRepo.findById(currentUserLoginSession.getUserId()).get();
			
			if(user.getUserType().equals("User"))
			{
				throw new FeedbackException("Only admins can access this feature");
			}
			
			List<Feedback> ansList = feedbackRepo.getAllFeedbacks();
			
			return ansList;
			
			
	}

}
