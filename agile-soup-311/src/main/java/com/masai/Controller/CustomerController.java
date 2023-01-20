package com.masai.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CustomerException;
import com.masai.Service.CustomerService;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> Ragistraion(@Valid @RequestBody Customer customer)throws CustomerException{
		Customer customer2=customerService.Ragistration(customer);
		return new ResponseEntity<Customer>(customer2,HttpStatus.CREATED);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer>GetCustomerDetailsByIdHandler(@PathVariable Integer id)throws CustomerException{
		Customer customer=customerService.getCustomerDetailsById(id);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<String>deleteCustomerhandler(@RequestBody LoginDTO loginDTO)throws CustomerException{
		String msg=customerService.deleteByMobileAndPassword(loginDTO);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
