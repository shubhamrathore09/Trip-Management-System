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
import org.springframework.web.bind.annotation.RestController;
import com.masai.Service.RouteService;
import com.masai.model.Routes;

@RestController
public class RouteController {

	@Autowired
	private RouteService routeService;
	
//	@PostMapping("/route")
//	public ResponseEntity<Routes> addRoutesHandler(@Valid @RequestBody Routes route){
//		return new ResponseEntity<>(routeService.createRoute(route),HttpStatus.CREATED);
//	}
	
//	@PutMapping("/route")
//	public ResponseEntity<Routes> updateRoutesHandler(@Valid @RequestBody Routes route){
//		return new ResponseEntity<>(routeService.updateRoute(route),HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/route/{id}")
//	public ResponseEntity<Routes> deleteRoutesHandler(@Valid @PathVariable("id") Integer id){
//		return new ResponseEntity<>(routeService.removeRoute(id),HttpStatus.OK);
//	}
//	
//	@GetMapping("/route/{id}")
//	public ResponseEntity<Routes> getRouteByIdHandler(@Valid @PathVariable("id") Integer id){
//		return new ResponseEntity<>(routeService.getRouteById(id),HttpStatus.OK);
//	}
//	
//	@GetMapping("/routes")
//	public ResponseEntity<List<Routes>> getAllRoutesHandler(){
//		return new ResponseEntity<>(routeService.getAllRoute(),HttpStatus.OK);
//	}
}
