package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.BookingService;
import com.masai.model.Booking;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public ResponseEntity<Booking> makeBooking(@RequestBody Booking booking) {
		Booking savedBooking = bookingService.makeBooking(booking);
		return new ResponseEntity<Booking>(savedBooking, HttpStatus.CREATED);
	}
}
