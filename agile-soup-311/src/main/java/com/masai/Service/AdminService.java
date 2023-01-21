package com.masai.Service;

import com.masai.Exception.AdminException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.model.Admin;
import com.masai.model.Bus;
import com.masai.model.CustomerDTO;
import com.masai.model.Routes;

import java.util.*;

public interface AdminService {
	
	public Admin InsertAdmin(Admin admin)throws AdminException;
	public Admin GetAdminById(Integer id,String key)throws AdminException,LoginException;
	
	public List<CustomerDTO> GetAllCustomerDetails(String key)throws CustomerException,LoginException;
	
	public Routes MakeRoute(Routes routes,String key)throws RouteException,LoginException;
	public String DeleteRoute(Integer routeId,String key)throws RouteException,LoginException;
	public List<Routes> routes(String key)throws RouteException,LoginException;
	public Routes findById(Integer id,String key)throws RouteException,LoginException;
	
	public Bus RagistorBus(Bus bus,String key)throws BusException,LoginException;
	public String RemoveBus(Integer id,String key)throws BusException,LoginException;
	public Bus GetBusById(Integer id ,String key)throws BusException,LoginException;
	public List<Bus> getAllTheBuses(String key)throws BusException,LoginException;
	public String AssingBusToRoute(String routeCode, String BusNumber,String key)throws LoginException,BusException,RouteException;
}
