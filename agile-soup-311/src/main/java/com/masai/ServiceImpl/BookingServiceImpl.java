package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BookingException;
import com.masai.Repository.BookingRepository;
import com.masai.Service.BookingService;
import com.masai.model.Booking;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public Booking makeBooking(Booking booking) throws BookingException {
		Booking b = bookingRepo.save(booking);
		if(b == null)
			throw new BookingException("Not able to save booking...");
		return b;
	}

	@Override
	public Booking cancelBooking(Integer id) throws BookingException {
		Optional<Booking> opt = bookingRepo.findById(id);
		if(opt.isEmpty())
			throw new BookingException("Booking is not found with the ID : "+ id + " Please provide correct ID.");
		Booking b = opt.get();
		bookingRepo.delete(b);
		return b;
	}

	@Override
	public Booking viewBooking(Integer id) throws BookingException {
		Optional<Booking> opt = bookingRepo.findById(id);
		if(opt.isEmpty())
			throw new BookingException("Booking is not found with the ID : "+ id + " Please provide correct ID.");
		return opt.get();
	}

	@Override
	public List<Booking> viewAllBooking() throws BookingException {
		List<Booking> list = bookingRepo.findAll();
		if(list.isEmpty())
			throw new BookingException("Booking list is empty...");
		return list;
	}

}
