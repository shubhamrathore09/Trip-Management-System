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

import com.masai.Exception.LoginException;
import com.masai.Exception.TravelsException;
import com.masai.Service.TravelsService;
import com.masai.model.Travels;

@RestController
public class TravelController {

	@Autowired
	private TravelsService travelService;
	
	@PostMapping("/AdminRegisterTravel")
	public ResponseEntity<Travels> addTravelsHandler(@Valid @RequestBody Travels travel,@RequestParam String key) throws TravelsException, LoginException{
		return new ResponseEntity<>(travelService.addTravels(travel,key),HttpStatus.CREATED);
	}
	
	@PutMapping("/AdminUpdateTravel")
	public ResponseEntity<Travels> updateTravelsHandler(@Valid @RequestBody Travels travel,@RequestParam String key) throws TravelsException, LoginException{
		return new ResponseEntity<>(travelService.updateTravels(travel,key),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/AdminDeleteTravelById/{id}")
	public ResponseEntity<Travels> deleteTravelsHandler(@Valid @PathVariable("id") Integer id,@RequestParam String key) throws TravelsException, LoginException{
		return new ResponseEntity<>(travelService.removeTravels(id,key),HttpStatus.OK);
	}
	
	@GetMapping("/GetTravelById/{id}")
	public ResponseEntity<Travels> getTravelByIdHandler(@Valid @PathVariable("id") Integer id,@RequestParam String key) throws TravelsException, LoginException{
		return new ResponseEntity<>(travelService.getTravelsById(id,key),HttpStatus.OK);
	}
	
	@GetMapping("/GetAllTravels")
	public ResponseEntity<List<Travels>> getAllTravelsHandler(@RequestParam String key) throws TravelsException, LoginException{
		return new ResponseEntity<>(travelService.getAllTravels(key),HttpStatus.OK);
	}
}
