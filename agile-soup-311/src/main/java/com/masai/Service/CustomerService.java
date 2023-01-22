package com.masai.Service;

import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.model.Bus;
import com.masai.model.Customer;
import com.masai.model.Hotel;
import com.masai.model.LoginDTO;
import com.masai.model.Routes;

import java.util.*;

public interface CustomerService {
	public Customer Ragistration(Customer customer)throws CustomerException;
	public Customer getCustomerDetailsById(Integer id,String key)throws CustomerException,LoginException;
	public String deleteByMobileAndPassword(LoginDTO loginDTO,String key)throws CustomerException,LoginException;
	public String changePassword(String oldPassword,String newPassword,String key)throws LoginException,CustomerException;
	
	public List<Routes> viewAllRoutes(String key)throws RouteException,LoginException;
	public Set<Bus> getBusBySourceAndDestincation(String routeFrom,String routeTo,String key)throws LoginException,RouteException;
	public String BookTicket(Integer quantity,String key,String BusNumber)throws LoginException,BusException; 
	
	public List<Hotel> viewAllHotelByAddress(String address,String key)throws HotelException,LoginException;
	public Hotel viewHotelByCode(Integer hoteCode ,String key)throws HotelException,LoginException;
	public List<Hotel> viewHotelByFare(Integer lowerAmount,Integer higherAmount,String key)throws LoginException,HotelException;
	public String BookHotel(Integer hotelCode,String key)throws HotelException,LoginException;
	
}
