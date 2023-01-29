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
import com.masai.Service.TravelsService;
import com.masai.model.Travels;

@RestController
public class TravelController {

	@Autowired
	private TravelsService travelService;
	
	@PostMapping("/travel")
	public ResponseEntity<Travels> addTravelsHandler(@Valid @RequestBody Travels travel,@RequestParam String key){
		return new ResponseEntity<>(travelService.addTravels(travel),HttpStatus.CREATED);
	}
	
	@PutMapping("/travel")
	public ResponseEntity<Travels> updateTravelsHandler(@Valid @RequestBody Travels travel,@RequestParam String key){
		return new ResponseEntity<>(travelService.updateTravels(travel),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/travel/{id}")
	public ResponseEntity<Travels> deleteTravelsHandler(@Valid @PathVariable("id") Integer id,@RequestParam String key){
		return new ResponseEntity<>(travelService.removeTravels(id),HttpStatus.OK);
	}
	
	@GetMapping("/travel/{id}")
	public ResponseEntity<Travels> getTravelByIdHandler(@Valid @PathVariable("id") Integer id){
		return new ResponseEntity<>(travelService.getTravelsById(id),HttpStatus.OK);
	}
	
	@GetMapping("/travels")
	public ResponseEntity<List<Travels>> getAllTravelsHandler(){
		return new ResponseEntity<>(travelService.getAllTravels(),HttpStatus.OK);
	}
}
