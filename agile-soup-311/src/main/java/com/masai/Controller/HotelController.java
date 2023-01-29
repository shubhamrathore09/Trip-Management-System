package com.masai.Controller;

import java.util.List;

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

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Service.HotelService;
import com.masai.model.Hotel;

@RestController
public class HotelController {
	
	@Autowired
	private HotelService hotelService;

	@PostMapping("/adminHotel")
	public ResponseEntity<Hotel> ragistorHotelHandler(@Valid @RequestBody Hotel hotel,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(hotelService.ragistorHotel(hotel, key),HttpStatus.CREATED);
	}
	
	@PutMapping("/adminHotel")
	public ResponseEntity<Hotel>UpdateHotelHandler(@Valid @RequestBody Hotel hotel,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel, key),HttpStatus.OK);
	}
	
	@GetMapping("/Hotel/{id}")
	public ResponseEntity<Hotel>GetHotelByIdHandler(@Valid @PathVariable("id")Integer id,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(hotelService.viewHotelById(id, key),HttpStatus.OK);
	}
	
	@DeleteMapping("/adminHotel/{id}")
	public ResponseEntity<String>DeleteHotelByIdHandler(@Valid @PathVariable("id")Integer id,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<String>(hotelService.deleteHotelById(id, key),HttpStatus.OK);
	}
	
	@GetMapping("/Hotels")
	public ResponseEntity<List<Hotel>>GetAllHotelIdHandler(@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<List<Hotel>>(hotelService.viewAllHotel(key),HttpStatus.OK);
	}
	
	@GetMapping("/HotelByAddress")
	public ResponseEntity<List<Hotel>>HotelsByAddressHandler(@RequestParam String key,@RequestParam String address) throws HotelException, LoginException{
	return new ResponseEntity<List<Hotel>>(hotelService.viewAllHotelByAddress(address,key),HttpStatus.OK);
	}
	
	@GetMapping("/HotelByFare")
	public ResponseEntity<List<Hotel>>HotelsByFareHandler(@RequestParam String key,@RequestParam Integer lowerAmount,@RequestParam Integer higherAmount) throws HotelException, LoginException{
	return new ResponseEntity<List<Hotel>>(hotelService.viewHotelByFare(lowerAmount,higherAmount,key),HttpStatus.OK);
	}
	
	@GetMapping("/HotelByCode/{code}")
	public ResponseEntity<Hotel>GetHotelByCodeHandler(@Valid @PathVariable("code")Integer code,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(hotelService.viewHotelByCode(code, key),HttpStatus.OK);
	}
	
}
