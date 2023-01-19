package com.masai.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.BookingRepository;
import com.masai.Service.BookingService;
import com.masai.model.Booking;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public Booking makeBooking(Booking booking) {
		return bookingRepo.save(booking);
	}

}
