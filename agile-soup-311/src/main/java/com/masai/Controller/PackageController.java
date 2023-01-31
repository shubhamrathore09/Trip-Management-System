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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BusException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Service.PackageService;
import com.masai.model.PackageModule;

@RestController
public class PackageController {

	@Autowired
	private PackageService packageService;

	@PostMapping("/AdminAddPackage")
	public ResponseEntity<PackageModule> addPackageHandler(@Valid @RequestBody PackageModule pack,@RequestParam String key) throws PackageException, LoginException, HotelException, BusException {
		return new ResponseEntity<PackageModule>(packageService.addPackage(pack,key), HttpStatus.CREATED);
	}

	@DeleteMapping("/AdminDeletePackageById/{id}")
	public ResponseEntity<PackageModule> deletePackageByIdHandler(@PathVariable("id") Integer id,@RequestParam String key) throws PackageException, LoginException {
		return new ResponseEntity<PackageModule>(packageService.deletePackage(id,key), HttpStatus.OK); 
	}

	@GetMapping("/GetPackageById/{id}")
	public ResponseEntity<PackageModule> searchPackageByIdHandler(@PathVariable("id") Integer id,@RequestParam String key) throws PackageException, LoginException {
		return new ResponseEntity<PackageModule>(packageService.searchPackage(id,key), HttpStatus.OK);
	}

	@GetMapping("/GetAllPackages")
	public ResponseEntity<List<PackageModule>> viewAllPackageHandler(@RequestParam String key) throws PackageException, LoginException {
		return new ResponseEntity<List<PackageModule>>(packageService.viewAllPackages(key), HttpStatus.OK);
	}
}
