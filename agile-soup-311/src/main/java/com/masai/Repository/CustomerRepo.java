package com.masai.Repository;


import java.util.List;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;
import com.masai.model.Hotel;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	public Customer findByCustomerMobile(String mobile);
	

	@Query("select new com.masai.model.CustomerDTO(c.customerName, c.customerEmail,c.customerMobile) from Customer c")
	public List<CustomerDTO> getAllCustomer();

	public Optional<Customer> findByCustomerEmail(String email);
	
	public Optional<Customer>  findByCustomerId(Integer customerId);
	
	

}
