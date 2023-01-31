package com.masai.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BookingException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.Service.BookingService;
import com.masai.Service.CustomerService;
import com.masai.Service.PackageService;
import com.masai.ServiceImpl.BookingServiceImpl;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.Customer;
import com.masai.model.Hotel;
import com.masai.model.LoginDTO;
import com.masai.model.PackageModule;
import com.masai.model.Routes;
import com.masai.model.Ticket;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	
	@PostMapping("/RegisterCustomer")
	public ResponseEntity<Customer> Ragistraion(@Valid @RequestBody Customer customer)throws CustomerException{
		Customer customer2=customerService.Ragistration(customer);
		return new ResponseEntity<Customer>(customer2,HttpStatus.CREATED);
	}
	
	@GetMapping("/GetCustomerById/{id}")
	public ResponseEntity<Customer>GetCustomerDetailsByIdHandler(@PathVariable Integer id,@RequestParam String key)throws CustomerException,LoginException{
		Customer customer=customerService.getCustomerDetailsById(id,key);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteCustomerById")
	public ResponseEntity<String>deleteCustomerhandler(@RequestBody LoginDTO loginDTO,@RequestParam String key)throws CustomerException,LoginException{
		String msg=customerService.deleteByMobileAndPassword(loginDTO,key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/UpdateCustomerById")
	public ResponseEntity<String> ChangePasswordHandler(@RequestParam String oldPassword,@RequestParam String newPassword, @RequestParam String key)
	throws LoginException,CustomerException
	{
		String msg=customerService.changePassword(oldPassword, newPassword, key);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
																		
	
}
