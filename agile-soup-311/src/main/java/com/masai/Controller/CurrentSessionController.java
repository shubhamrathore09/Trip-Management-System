package com.masai.Controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.CurrentSessionService;

import com.masai.model.LoginDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CurrentSessionController {
	
	@Autowired
	private CurrentSessionService currentSessionService;
	
	@GetMapping("/login")
	public ResponseEntity<String> LoginInSystem(@RequestBody LoginDTO loginDTO)throws LoginException{
		String msg=currentSessionService.LoginInSystem(loginDTO);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String>LogoutFromSystem(@RequestParam String key)throws LoginException{
		String msg=currentSessionService.LogoutFromSystem(key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
}
