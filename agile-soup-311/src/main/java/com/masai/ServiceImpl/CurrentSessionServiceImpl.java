package com.masai.ServiceImpl;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.AdminRepo;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Service.CurrentSessionService;
import com.masai.model.Admin;
import com.masai.model.CurrentLoginSession;
import com.masai.model.LoginDTO;

import net.bytebuddy.utility.RandomString;

@Service
public class CurrentSessionServiceImpl implements CurrentSessionService{
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public String LoginInSystem(LoginDTO loginDTO) throws LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserMobile(loginDTO.getMobile());
		
		if(currentLoginSession==null) {
			
			Admin admin=adminRepo.findByAdminMobile(loginDTO.getMobile());
			
			if(admin==null) {
				throw new LoginException("wrong number");
			}
			else {
				if(admin.getAdminPassword().equals(loginDTO.getPassword())) {
					String key=RandomString.make(6);
					
					CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
					currentLoginSession2.setUserMobile(loginDTO.getMobile());
					currentLoginSession2.setUserKey(key);
					
					currentSessionRepo.save(currentLoginSession2);
					
					return "login successfully :"+key;
				}
				else {
					throw new LoginException("wrong password");
				}
			}
			
		}
		else {
			throw new LoginException("Already Login");
		}
	
	}

	@Override
	public String LogoutFromSystem(String key) throws LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("you are not login");
		}
		
		currentSessionRepo.delete(currentLoginSession);
		
		return "Logout Succesfully";
		
		
	}

}
