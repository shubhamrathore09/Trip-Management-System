package com.masai.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CustomerException;
import com.masai.Service.CustomerService;
import com.masai.model.Customer;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> Ragistraion(@Valid @RequestBody Customer customer)throws CustomerException{
		Customer customer2=customerService.Ragistration(customer);
		return new ResponseEntity<Customer>(customer2,HttpStatus.CREATED);
	}
	
}
