package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.HotelBookingRepository;
import com.masai.Repository.HotelRepository;
import com.masai.Service.HotelBookingService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Hotel;
import com.masai.model.HotelBooking;


@Service
public class HotelBookingServiceImpl implements HotelBookingService{
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private HotelBookingRepository hotelBookingRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public HotelBooking bookHotel(HotelBooking hotelbooking, String key) throws LoginException, HotelException {
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		
		Hotel hotel=hotelRepository.findByHotelCode(hotelbooking.getHotelCode());
		
		if(hotel==null) {
			throw new HotelException("wrong hotel code "+hotelbooking.getHotelCode());
		}
		
		hotelbooking.setBookingNumber(currentLoginSession.getUserMobile());
		
		return hotelBookingRepository.save(hotelbooking);
	}

	@Override
	public HotelBooking viewHotelBookingByid(Integer id, String key) throws LoginException, HotelException {
		
      CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<HotelBooking> hotelbooking=hotelBookingRepository.findById(id);
		
		if(hotelbooking.isEmpty()) {
			throw new HotelException("hotel not found by id "+id);
		}
		
		return hotelbooking.get();
		
	}

	@Override
	public HotelBooking cencleHotelBookingById(Integer id, String key) throws LoginException, HotelException {
		
		 CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
			
			if(currentLoginSession==null) {
				throw new LoginException("You have to login first");
			}
			
			Optional<HotelBooking> hotelbooking=hotelBookingRepository.findById(id);
			
			if(hotelbooking.isEmpty()) {
				throw new HotelException("hotel not found by id "+id);
			}
			
			HotelBooking hotelBooking2=hotelbooking.get();
			
			hotelBookingRepository.delete(hotelBooking2);
			
			return hotelBooking2;
		
		
	}

	@Override
	public List<HotelBooking> viewOfHotelBooking(String key) throws LoginException, HotelException {
		

		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		List<HotelBooking> hotelbooking=hotelBookingRepository.findAll();
		
		if(hotelbooking.size()==0) {
			throw new HotelException("No booking done right now");
		}
		
		return hotelbooking;
	}

}
