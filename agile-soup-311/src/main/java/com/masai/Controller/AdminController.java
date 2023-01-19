package com.masai.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.AdminException;
import com.masai.Service.AdminService;
import com.masai.model.Admin;

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
	public ResponseEntity<Admin> GetByIdHandler( @PathVariable Integer id)throws AdminException{
		Admin admin=adminService.GetAdminById(id);
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
	
}
