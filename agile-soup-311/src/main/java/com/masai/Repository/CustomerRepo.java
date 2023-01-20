package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	public Customer findByCustomerMobile(String mobile);
	
	public Optional<Customer> findByCustomerEmail(String email);
	
	public Optional<Customer>  findByCustomerId(Integer customerId);
}
