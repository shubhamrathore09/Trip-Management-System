package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AdminException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.Repository.AdminRepo;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.HotelRepository;
import com.masai.Repository.PackageRepository;
import com.masai.Repository.RouteRepository;
import com.masai.Service.AdminService;
import com.masai.model.Admin;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.CustomerDTO;
import com.masai.model.Hotel;
import com.masai.model.PackageModule;
import com.masai.model.Routes;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;


	@Override
	public Admin InsertAdmin(Admin admin,Integer code) throws AdminException {
		
		Admin admin2=adminRepo.findByAdminMobile(admin.getAdminMobile());
		
		if(admin2==null) {
			
			if(code==0000) {
			return adminRepo.save(admin);
			}
			else {
				throw new AdminException("please enter the code for ragistration");
			}
		}
		
		throw new AdminException("admin already present by that number");
		
	}

	@Override
	public Admin GetAdminById(Integer id,String key) throws AdminException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		return adminRepo.findById(id).orElseThrow(()->new AdminException("please right id"));
				
	}

	@Override
	public List<CustomerDTO> GetAllCustomerDetails(String key) throws CustomerException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<CustomerDTO> list=customerRepo.getAllCustomer();
		
		if(list==null)
		{
			throw new CustomerException("no customer are available");
		}
		else
		return list;
	}
	





}
