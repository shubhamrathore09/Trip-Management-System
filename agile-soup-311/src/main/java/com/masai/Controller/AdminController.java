package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.masai.Exception.AdminException;
import com.masai.Exception.BookingException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.Service.AdminService;
import com.masai.Service.BookingService;
import com.masai.Service.PackageService;
import com.masai.Service.TravelsService;
import com.masai.model.Admin;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.CustomerDTO;
import com.masai.model.Hotel;
import com.masai.model.PackageModule;
import com.masai.model.Routes;
import com.masai.model.Ticket;
import com.masai.model.Travels;

@RestController
public class AdminController {
	

	@Autowired 
	private AdminService adminService;
	
	
	@Autowired
	private BookingService bookingService;
	
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> InsertAdminHandler(@Valid @RequestBody Admin admin)throws AdminException{
		Admin admin2=adminService.InsertAdmin(admin);
		return new ResponseEntity<Admin>(admin2,HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> GetByIdHandler(@RequestParam String key, @PathVariable Integer id)throws AdminException,LoginException{
		Admin admin=adminService.GetAdminById(id,key);
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomer(@RequestParam String key)throws CustomerException,LoginException{
		List<CustomerDTO> customerDTO=adminService.GetAllCustomerDetails(key);
		return new ResponseEntity<List<CustomerDTO>>(customerDTO,HttpStatus.OK);
	}


	
	
//	------------------------Booking--------------------------------
	
	
	@PostMapping("/Abooking/{otp}")
	public ResponseEntity<Ticket> makeBookingHandler(@RequestBody Booking booking,@RequestParam String key,@PathVariable("otp")Integer otp)throws BookingException,LoginException {
		return new ResponseEntity<Ticket>(bookingService.makeBooking(booking,key,otp), HttpStatus.CREATED);
	}

	@DeleteMapping("/Abooking/{id}")
	public ResponseEntity<Ticket> cancelBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<>(bookingService.cancelBooking(id,key), HttpStatus.OK);
	}

	@GetMapping("/Abooking/{id}")
	public ResponseEntity<Booking> viewBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<Booking>(bookingService.viewBooking(id,key), HttpStatus.OK);
	}
	
	@GetMapping("/listOfBooking")
	public ResponseEntity<List<Booking>> viewAllBookingHandler(@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<List<Booking>>(bookingService.viewAllBooking(key), HttpStatus.OK);
	}
	
}
