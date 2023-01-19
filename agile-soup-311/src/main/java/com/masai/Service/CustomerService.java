package com.masai.Service;

import com.masai.Exception.CustomerException;
import com.masai.model.Customer;

public interface CustomerService {
	
	public Customer Ragistration(Customer customer)throws CustomerException;
	
}
