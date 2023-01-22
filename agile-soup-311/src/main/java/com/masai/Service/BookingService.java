package com.masai.Service;

import java.util.List;

import com.masai.Exception.BookingException;
import com.masai.Exception.LoginException;
import com.masai.model.Booking;

public interface BookingService {
	public Booking makeBooking(Booking booking,String key) throws BookingException,LoginException;
	
	public Booking cancelBooking(Integer id,String key) throws BookingException,LoginException;
	
	public Booking viewBooking(Integer id,String key) throws BookingException,LoginException;
	
	public List<Booking> viewAllBooking(String key) throws BookingException,LoginException;
}
