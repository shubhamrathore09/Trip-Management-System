package com.masai.Service;

import com.masai.Exception.AdminException;
import com.masai.model.Admin;

public interface AdminService {
	
	public Admin InsertAdmin(Admin admin)throws AdminException;
	public Admin GetAdminById(Integer id)throws AdminException;
	
}
