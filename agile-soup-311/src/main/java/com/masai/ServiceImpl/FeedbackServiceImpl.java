package com.masai.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CustomerException;
import com.masai.Exception.FeedbackException;
import com.masai.Exception.LoginException;
import com.masai.Repository.AdminRepo;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.FeedbackRepository;
import com.masai.Service.FeedbackService;
import com.masai.model.Admin;
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
	
	@Autowired
	private AdminRepo adminRepo;


	@Override
	public Feedback addFeedback(Feedback feedback, String authKey)
			throws FeedbackException, LoginException, CustomerException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(authKey);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<Admin> admin=adminRepo.findById(feedback.getPersonId());
		Optional<Customer> customer=customerRepo.findById(feedback.getPersonId());
		
		if(admin.isPresent() || customer.isPresent()) 
		{
			
		}
		
//		Admin admin1=admin.get();
//		Customer customer2=customer.get();
//		
//		if(admin1==null || customer2==null) {
//			
//		}
		
		return null;
	}


	@Override
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Feedback> findByCustomerId(Integer customerId, String authKey)
			throws FeedbackException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
