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
					if(customer.getCustomerPassword().equals(loginDTO.getPassword()) && loginDTO.getUserType().equals("CUSTOMER")) {
						String key=RandomString.make(6)+customer.getCustomerId()+"Customer";
						CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
						currentLoginSession2.setUserKey(key);
						currentLoginSession2.setUserMobile(loginDTO.getMobile());
						currentLoginSession2.setUserType("CUSTOMER");
						currentSessionRepo.save(currentLoginSession2);
						return "login succesfully: "+key;
					}else {
		
						throw new LoginException("Check User Type");
					}
				}
	
			}else {
				
				if(admin.getAdminPassword().equals(loginDTO.getPassword()) && loginDTO.getUserType().equals("ADMIN")){
					String key=RandomString.make(6)+admin.getAdminId()+"Admin";
					CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
					currentLoginSession2.setUserKey(key);
					currentLoginSession2.setUserMobile(loginDTO.getMobile());
					currentLoginSession2.setUserType("ADMIN");
					currentSessionRepo.save(currentLoginSession2);
					return "login succesfully: "+key;
				}else {
					throw new LoginException("Check User Type");
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
