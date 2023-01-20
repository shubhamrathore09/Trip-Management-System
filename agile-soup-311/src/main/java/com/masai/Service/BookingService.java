package com.masai.Service;

import java.util.List;

import com.masai.Exception.BookingException;
import com.masai.model.Booking;

public interface BookingService {
	public Booking makeBooking(Booking booking) throws BookingException;
	
	public Booking cancelBooking(Integer id) throws BookingException;
	
	public Booking viewBooking(Integer id) throws BookingException;
	
	public List<Booking> viewAllBooking() throws BookingException;
}
