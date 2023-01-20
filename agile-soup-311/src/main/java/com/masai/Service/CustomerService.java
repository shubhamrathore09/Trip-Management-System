package com.masai.Service;

import com.masai.Exception.CustomerException;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;

public interface CustomerService {
	public Customer Ragistration(Customer customer)throws CustomerException;
	public Customer getCustomerDetailsById(Integer id)throws CustomerException;
	public String deleteByMobileAndPassword(LoginDTO loginDTO)throws CustomerException;
}
