package com.masai.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.masai.Exception.CustomerException;
import com.masai.Repository.CustomerRepo;
import com.masai.Service.CustomerService;
import com.masai.model.Customer;

@Service
@Validated
public class CustomerServiceimpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	

	@Override
	public Customer Ragistration(Customer customer) throws CustomerException {
		return customerRepo.save(customer);
	}

}
