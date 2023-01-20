package com.masai.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CustomerException;
import com.masai.Repository.CustomerRepo;
import com.masai.Service.CustomerService;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;



@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	

	@Override
	public Customer Ragistration(Customer customer) throws CustomerException {
		Customer customer2=customerRepo.findByCustomerMobile(customer.getCustomerMobile());
		if(customer2==null) {
			return customerRepo.save(customer);
		}
		else {
			throw new CustomerException("customer already exist by that number");
		}
	}


	@Override
	public Customer getCustomerDetailsById(Integer id) throws CustomerException {
		return customerRepo.findById(id).orElseThrow(()->new CustomerException("Customer not available by that id: "+id));
	}


	@Override
	public String deleteByMobileAndPassword(LoginDTO loginDTO) throws CustomerException {
		
		Customer customer=customerRepo.findByCustomerMobile(loginDTO.getMobile());
		if(customer==null) {
			throw new CustomerException("You have enter wrong number");
		}
		
		else {
			if(customer.getCustomerPassword().equals(loginDTO.getPassword())){
				customerRepo.delete(customer);
				return "customer have been delete successfully";
			}
			else {
				throw new CustomerException("you have enter wrong password");
			}
		}
		
	}

}
