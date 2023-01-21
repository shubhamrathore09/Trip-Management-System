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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.AdminException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Service.AdminService;
import com.masai.model.Admin;
import com.masai.model.Bus;
import com.masai.model.CustomerDTO;
import com.masai.model.Routes;

@RestController
public class AdminController {
	

	@Autowired 
	private AdminService adminService;
	
	
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
	
	@PostMapping("/route")
	public ResponseEntity<Routes> addRoutesHandler(@RequestParam String key,@Valid @RequestBody Routes route)throws LoginException{
		return new ResponseEntity<>(adminService.MakeRoute(route,key),HttpStatus.CREATED);
	}
		
	@DeleteMapping("/route/{id}")
	public ResponseEntity<String> deleteRoutesHandler(@RequestParam String key,@Valid @PathVariable("id") Integer id)throws LoginException{
		return new ResponseEntity<>(adminService.DeleteRoute(id,key),HttpStatus.OK);
	}
	@GetMapping("/routes")
	public ResponseEntity<List<Routes>> getAllRoutesHandler(@RequestParam String key)throws LoginException{
		return new ResponseEntity<>(adminService.routes(key),HttpStatus.OK);
	}
	
	@GetMapping("/route/{id}")
	public ResponseEntity<Routes> getRouteByIdHandler(@RequestParam String key,@Valid @PathVariable("id") Integer id)throws LoginException{
		return new ResponseEntity<>(adminService.findById(id,key),HttpStatus.OK);
	}
	
//	***************************************************Bus****************************************************
	
	@PostMapping("/Bus")
	public ResponseEntity<Bus> RagistorBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException,LoginException{
		Bus bus2=adminService.RagistorBus(bus, key);
		return new ResponseEntity<Bus>(bus2,HttpStatus.CREATED);
	}
	
	@GetMapping("/Bus/{id}")
	public ResponseEntity<Bus> GetBusByIdHandler(@PathVariable Integer id ,@RequestParam String key)throws BusException,LoginException{
		Bus bus2=adminService.GetBusById(id, key);
		return new ResponseEntity<Bus>(bus2,HttpStatus.OK);
	}
	
	@GetMapping("/Buses")
	public ResponseEntity<List<Bus>> GetAllBusHandler(@RequestParam String key)throws BusException,LoginException{
		List<Bus> bus2=adminService.getAllTheBuses(key);
		return new ResponseEntity<List<Bus>>(bus2,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/Bus/{id}")
	public ResponseEntity<String> DeleteBusByIdHandler(@PathVariable Integer id ,@RequestParam String key)throws BusException,LoginException{
		String msg=adminService.RemoveBus(id, key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/Bus")
	public ResponseEntity<String>AssignBusByRouteHandler(@RequestParam String routeCode,@RequestParam String BusNumber,@RequestParam String key)
			throws LoginException,BusException,RouteException{
		String msg=adminService.AssingBusToRoute(routeCode, BusNumber, key);
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
}
