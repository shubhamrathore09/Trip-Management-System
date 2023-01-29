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
import com.masai.Service.RouteService;
import com.masai.model.Routes;

@RestController
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@PostMapping("/route")
	public ResponseEntity<Routes> addRoutesHandler(@RequestParam String key,@Valid @RequestBody Routes route)throws LoginException{
		return new ResponseEntity<>(routeService.addRoute(route,key),HttpStatus.CREATED);
	}
		
	@DeleteMapping("/route/{id}")
	public ResponseEntity<String> deleteRoutesHandler(@RequestParam String key,@Valid @PathVariable("id") Integer id)throws LoginException{
		return new ResponseEntity<>(routeService.DeleteRoute(id,key),HttpStatus.OK);
	}
	@GetMapping("/routes")
	public ResponseEntity<List<Routes>> getAllRoutesHandler(@RequestParam String key)throws LoginException{
		return new ResponseEntity<>(routeService.routes(key),HttpStatus.OK);
	}
	
	@GetMapping("/route/{id}")
	public ResponseEntity<Routes> getRouteByIdHandler(@RequestParam String key,@Valid @PathVariable("id") Integer id)throws LoginException{
	  	return new ResponseEntity<>(routeService.findById(id,key),HttpStatus.OK);
	}
}
