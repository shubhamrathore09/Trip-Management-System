package com.masai.ServiceImpl;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.CurrentSessionRepo;
import com.masai.Service.CurrentSessionService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.LoginDTO;

@Service
public class CurrentSessionServiceImpl implements CurrentSessionService{
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Override
	public String LoginInSystem(LoginDTO loginDTO) throws LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByMobile(loginDTO.getMobile());
		
		if(currentLoginSession==null) {
			
			
			
			return null;
			
		}
		else {
			throw new LoginException("Already Login");
		}
	
	}

}
