package com.masai.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AdminException;
import com.masai.Repository.AdminRepo;
import com.masai.Service.AdminService;
import com.masai.model.Admin;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public Admin InsertAdmin(Admin admin) throws AdminException {
		
		Admin admin2=adminRepo.findByAdminMobile(admin.getAdminMobile());
		
		if(admin2==null) {
		
			return adminRepo.save(admin);
		}
		
		throw new AdminException("admin already present by that number");
		
	}

	@Override
	public Admin GetAdminById(Integer id) throws AdminException {
		
		return adminRepo.findById(id).orElseThrow(()->new AdminException("please right id"));
				
	}

}
