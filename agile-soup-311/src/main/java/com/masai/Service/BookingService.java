package com.masai.Service;

import java.util.List;

import com.masai.Exception.BookingException;
import com.masai.Exception.LoginException;
import com.masai.model.Booking;
import com.masai.model.Ticket;

public interface BookingService {
	public Ticket makeBooking(Booking booking,String key,Integer otp) throws BookingException,LoginException;
	
	public Ticket cancelBooking(Integer id,String key) throws BookingException,LoginException;
	
	public Ticket viewBooking(Integer id,String key) throws BookingException,LoginException;
	
	public List<Booking> viewAllBooking(String key) throws BookingException,LoginException;
}
