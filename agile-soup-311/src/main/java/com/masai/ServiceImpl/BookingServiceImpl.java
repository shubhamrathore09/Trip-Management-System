package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BookingException;
import com.masai.Exception.LoginException;
import com.masai.Repository.BookingRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Service.BookingService;
import com.masai.model.Booking;
import com.masai.model.CurrentLoginSession;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	@Override
	public Booking makeBooking(Booking booking,String key) throws BookingException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		booking.setUserId(currentLoginSession.getUserMobile());
		Booking b = bookingRepo.save(booking);
		if(b == null)
			throw new BookingException("Not able to save booking...");
		return b;
	}

	@Override
	public Booking cancelBooking(Integer id,String key) throws BookingException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		Optional<Booking> opt = bookingRepo.findById(id);
		if(opt.isEmpty())
			throw new BookingException("Booking is not found with the ID : "+ id + " Please provide correct ID.");
		Booking b = opt.get();
		if(b.getUserId().equals(currentLoginSession.getUserMobile())) {
			bookingRepo.delete(b);
			return b;
		}else {
			throw new LoginException("Login with ordered account");
		}
		
	}

	@Override
	public Booking viewBooking(Integer id,String key) throws BookingException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		Optional<Booking> opt = bookingRepo.findById(id);
		if(opt.isEmpty())
			throw new BookingException("Booking is not found with the ID : "+ id + " Please provide correct ID.");
		
		Booking b = opt.get();
		if(b.getUserId().equals(currentLoginSession.getUserMobile()) || currentLoginSession.getUserType().equals("ADMIN")) {
			return b;
		}else {
			throw new LoginException("Login with ordered account");
		}
		
		
	}

	@Override
	public List<Booking> viewAllBooking(String key) throws BookingException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		List<Booking> list = bookingRepo.findAll();
		if(list.isEmpty())
			throw new BookingException("Booking list is empty...");
		return list;
	}

}
