package com.masai.Controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Service.HotelBookingService;
import com.masai.model.HotelBooking;



@RestController
@RequestMapping("Hotel")
public class HotelBookingController {
	
	@Autowired
	private HotelBookingService hotelBookingService;
	
	@PostMapping("/Booking")
	public ResponseEntity<HotelBooking> HotelBookingHandler(@Valid @RequestBody HotelBooking hotelBooking,@RequestParam String key) throws HotelException, LoginException{
	
		return new ResponseEntity<>(hotelBookingService.bookHotel(hotelBooking, key),HttpStatus.CREATED);
	}
	
	@GetMapping("/GetBookingById")
	public ResponseEntity<HotelBooking> HotelBookingViewHandler(@RequestParam Integer id,@RequestParam String key) throws HotelException, LoginException{
	
		return new ResponseEntity<>(hotelBookingService.viewHotelBookingByid(id, key),HttpStatus.OK);
	}
	
	@GetMapping("/AdminGetAllBookings")
	public ResponseEntity<List<HotelBooking>> HotelViewsHandler(@RequestParam String key) throws HotelException, LoginException{
	
		return new ResponseEntity<>(hotelBookingService.viewOfHotelBooking( key),HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteBookingById")
	public ResponseEntity<HotelBooking> HotelBookingDeleteHandler(@RequestParam Integer id,@RequestParam String key) throws HotelException, LoginException{
		return new ResponseEntity<>(hotelBookingService.cencleHotelBookingById(id, key),HttpStatus.OK);
	}
	
}
