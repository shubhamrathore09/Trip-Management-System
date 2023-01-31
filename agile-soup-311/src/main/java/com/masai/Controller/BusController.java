package com.masai.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BusException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Service.BusService;
import com.masai.model.Bus;

@RestController
public class BusController {
	
	@Autowired
	private BusService busService;
	

	@PostMapping("/AdminRegisterBus")
	public ResponseEntity<Bus> RegistorBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException,LoginException{
		Bus bus2=busService.RegistorBus(bus, key);
		return new ResponseEntity<Bus>(bus2,HttpStatus.CREATED);
	}
	
	@GetMapping("/GetBusById/{id}")
	public ResponseEntity<Bus> GetBusByIdHandler(@PathVariable Integer id ,@RequestParam String key)throws BusException,LoginException{
		Bus bus2=busService.GetBusById(id, key);
		return new ResponseEntity<Bus>(bus2,HttpStatus.OK);
	}
	
	@GetMapping("/GetAllBuses")
	public ResponseEntity<List<Bus>> GetAllBusHandler(@RequestParam String key)throws BusException,LoginException{
		List<Bus> bus2=busService.getAllTheBuses(key);
		return new ResponseEntity<List<Bus>>(bus2,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/AdminDeleteBusById/{id}")
	public ResponseEntity<String> DeleteBusByIdHandler(@PathVariable Integer id ,@RequestParam String key)throws BusException,LoginException{
		String msg=busService.RemoveBus(id, key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
//	@GetMapping("/BusAssign")
//	public ResponseEntity<String>AssignBusByRouteHandler(@RequestParam String routeCode,@RequestParam String BusNumber,@RequestParam String key)
//			throws LoginException,BusException,RouteException{
//		String msg=busService.AssingBusToRoute(routeCode, BusNumber, key);
//		return new ResponseEntity<>(msg,HttpStatus.CREATED);
//	}
	
	@GetMapping("/GetBusByRoutes")
	public ResponseEntity<Set<Bus>> getAllBusBySourceToHandler(@RequestParam String routeFrom,String routeTo,String key,@DateTimeFormat(iso = ISO.DATE)  LocalDate date)throws LoginException,RouteException,BusException{
		Set<Bus> set=busService.getBusBySourceAndDestincation(routeFrom, routeTo, key,date);
		return new ResponseEntity<Set<Bus>>(set,HttpStatus.OK);
	}
}
