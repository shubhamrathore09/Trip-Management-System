package com.masai.Controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Service.CustomerService;
import com.masai.model.Bus;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.model.Routes;

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
	public ResponseEntity<Customer>GetCustomerDetailsByIdHandler(@PathVariable Integer id,@RequestParam String key)throws CustomerException,LoginException{
		Customer customer=customerService.getCustomerDetailsById(id,key);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<String>deleteCustomerhandler(@RequestBody LoginDTO loginDTO,@RequestParam String key)throws CustomerException,LoginException{
		String msg=customerService.deleteByMobileAndPassword(loginDTO,key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<String> ChangePasswordHandler(@RequestParam String oldPassword,@RequestParam String newPassword, @RequestParam String key)
	throws LoginException,CustomerException
	{
		String msg=customerService.changePassword(oldPassword, newPassword, key);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/customerRoute")
	public ResponseEntity<List<Routes>> GetAllRouteHandler(@RequestParam String key)throws LoginException,CustomerException{
		List<Routes> list=customerService.viewAllRoutes(key);
		return new ResponseEntity<List<Routes>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/customerBus")
	public ResponseEntity<Set<Bus>> getAllBusBySourceToHandler(String routeFrom,String routeTo,String key)throws LoginException,RouteException{
		Set<Bus> set=customerService.getBusBySourceAndDestincation(routeFrom, routeTo, key);
		return new ResponseEntity<Set<Bus>>(set,HttpStatus.OK);
	}
	
	@PutMapping("/CustomerTicket")
	public ResponseEntity<String>BookTicketOfBusHandler(@RequestParam Integer quantity, @RequestParam String key,@RequestParam String BusNumber)
	throws LoginException,BusException{
		String msg=customerService.BookTicket(quantity, key, BusNumber);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
