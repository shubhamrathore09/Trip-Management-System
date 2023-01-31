package com.masai.Service;

import java.util.List;

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.model.Hotel;

public interface HotelService {

	public Hotel ragistorHotel(Hotel hotel,String key)throws HotelException,LoginException;
	
	public Hotel updateHotel(Hotel hotel, String key)throws HotelException,LoginException;
	
	
	
	public String deleteHotelById(Integer id,String ke)throws HotelException,LoginException;
	
	public String BookHotel(String hotelCode,String key)throws HotelException,LoginException;
	
	public Hotel viewHotelById(Integer id,String key)throws HotelException,LoginException;
	
	public List<Hotel> viewAllHotel(String key)throws HotelException,LoginException;
	
	
//	public Hotel viewHotelById(Integer id,String key)throws HotelException,LoginException;
//	public List<Hotel> viewAllHotel(String key)throws HotelException,LoginException;
	
	public List<Hotel> viewAllHotelByAddress(String address,String key)throws HotelException,LoginException;
	public Hotel viewHotelByCode(String hotelCode ,String key)throws HotelException,LoginException;
	public List<Hotel> viewHotelByFare(Integer lowerAmount,Integer higherAmount,String key)throws LoginException,HotelException;

}
