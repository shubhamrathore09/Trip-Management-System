package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BookingException;
import com.masai.Exception.LoginException;
import com.masai.Service.BookingService;
import com.masai.model.Booking;

@RestController
@RequestMapping("/bookings")
public class BookingController {

//	@Autowired
//	private BookingService bookingService;
//
//	@PostMapping("/booking")
//	public ResponseEntity<Booking> makeBookingHandler(@RequestBody Booking booking,@RequestParam String key)throws BookingException,LoginException {
//		return new ResponseEntity<Booking>(bookingService.makeBooking(booking,key), HttpStatus.CREATED);
//	}
//
//	@DeleteMapping("/booking/{id}")
//	public ResponseEntity<Booking> cancelBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
//		return new ResponseEntity<Booking>(bookingService.cancelBooking(id), HttpStatus.OK);
//	}
//
//	@GetMapping("/booking/{id}")
//	public ResponseEntity<Booking> viewBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
//		return new ResponseEntity<Booking>(bookingService.viewBooking(id), HttpStatus.OK);
//	}
//
//	@GetMapping("/listOfBooking")
//	public ResponseEntity<List<Booking>> viewAllBookingHandler(@RequestParam String key)throws BookingException,LoginException {
//		return new ResponseEntity<List<Booking>>(bookingService.viewAllBooking(), HttpStatus.OK);
//	}
}
