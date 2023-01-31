package com.masai.Service;

import com.masai.Exception.AdminException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.model.Admin;
import com.masai.model.Bus;
import com.masai.model.CustomerDTO;
import com.masai.model.Hotel;
import com.masai.model.PackageModule;
import com.masai.model.Routes;

import java.util.*;

public interface AdminService {
	
	public Admin InsertAdmin(Admin admin,Integer code)throws AdminException;
	public Admin GetAdminById(Integer id,String key)throws AdminException,LoginException;
	
	public List<CustomerDTO> GetAllCustomerDetails(String key)throws CustomerException,LoginException;


	
}
