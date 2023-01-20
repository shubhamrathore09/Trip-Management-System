package com.masai.ServiceImpl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.LoginException;
import com.masai.Repository.AdminRepo;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Service.CurrentSessionService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;

import net.bytebuddy.utility.RandomString;

import com.masai.model.Admin;

@Service
public class CurrentSessionServiceImpl implements CurrentSessionService{
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	
	@Override
	public String LoginInSystem(LoginDTO loginDTO)throws LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserMobile(loginDTO.getMobile());
		
		if(currentLoginSession==null) {
			
	     Admin admin=adminRepo.findByAdminMobile(loginDTO.getMobile());

			if(admin==null) {
				
				Customer customer=customerRepo.findByCustomerMobile(loginDTO.getMobile());
				
				if(customer==null) {
					throw new LoginException("wrong number");
				}
				
				else {
					if(customer.getCustomerPassword().equals(loginDTO.getPassword())) {
						
						String key=RandomString.make(6);
						CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
						currentLoginSession2.setUserKey(key);
						currentLoginSession2.setUserMobile(loginDTO.getMobile());
						currentSessionRepo.save(currentLoginSession2);
						
						return "login succesfully: "+key;
					}
				}
				
			  	
				
			}
			
			
			else {
				if(admin.getAdminPassword().equals(loginDTO.getPassword())) {
					String key=RandomString.make(6);
					CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
					currentLoginSession2.setUserKey(key);
					currentLoginSession2.setUserMobile(loginDTO.getMobile());
					currentSessionRepo.save(currentLoginSession2);
					return "login succesfully: "+key;
				}
			}
				
		}
	
			throw new LoginException("Already Login");
	
	
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
