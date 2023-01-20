package com.masai.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Customer;
import com.masai.model.CustomerDTO;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	public Customer findByCustomerMobile(String mobile);
	
	@Query("select new com.masai.model.CustomerDTO(c.customerName, c.customerEmail,c.customerMobile) from Customer c")
	public List<CustomerDTO> getAllCustomer();
}
