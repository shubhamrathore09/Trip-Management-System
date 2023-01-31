package com.masai.Service;

import java.util.List;

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.model.HotelBooking;



public interface HotelBookingService {
	
	public HotelBooking bookHotel(HotelBooking hotelbooking ,String key)throws LoginException,HotelException;
	public HotelBooking viewHotelBookingByid(Integer id,String key)throws LoginException,HotelException;
	public HotelBooking cencleHotelBookingById(Integer id,String key)throws LoginException,HotelException;
	public List<HotelBooking> viewOfHotelBooking(String key)throws LoginException,HotelException;
}
