package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.HotelRepository;
import com.masai.Service.HotelService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Hotel;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	
	@Override
	public Hotel ragistorHotel(Hotel hotel, String key) throws HotelException, LoginException {
		
		 CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
			
			if(currentLoginSession==null) {
				throw new LoginException("You have to login first");
			}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
				throw new LoginException("Please Login As Admin");
			}
			
			Hotel hotel2=hotelRepository.findByHotelCode(hotel.getHotelCode());
			
			if(hotel2==null) {
				return hotelRepository.save(hotel);
			}
		throw new HotelException("hotel is already present by that id");
	}

	@Override
	public Hotel updateHotel(Hotel hotel, String key) throws HotelException, LoginException {
		
		 CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
			
			if(currentLoginSession==null) {
				throw new LoginException("You have to login first");
			}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
				throw new LoginException("Please Login As Admin");
			}
			
			Optional<Hotel> hotel2=hotelRepository.findById(hotel.getHotelID());
		
			if(hotel2.isPresent()) {
				return hotelRepository.save(hotel);
			}
		
			throw new HotelException("you have enter wrong id");
			
	}

	@Override
	public String deleteHotelById(Integer id, String key) throws HotelException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<Hotel> opt=hotelRepository.findById(id);
		
		if(opt.isPresent()) {
			hotelRepository.delete(opt.get());
			return "hotel has been deleted";	
		}
		throw new HotelException("no hotel available by that id");
	}

	@Override
	public Hotel viewHotelById(Integer id, String key) throws HotelException, LoginException {
		
     CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<Hotel> opt=hotelRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();	
		}
		throw new HotelException("no hotel available by that id");
	}

	@Override
	public List<Hotel> viewAllHotel(String key) throws HotelException, LoginException {
		
		  CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
			
			if(currentLoginSession==null) {
				throw new LoginException("You have to login first");
			}
			List<Hotel> list=hotelRepository.findAll();
			if(list==null) {
				throw new HotelException("no hotels are available");
			}
			else
			{
				return list;
			}
	}
	
	
	@Override
	public List<Hotel> viewAllHotelByAddress(String address, String key) throws HotelException, LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<Hotel> list=hotelRepository.findByAddress(address);
		
		if(list==null) {
			throw new HotelException("hotel not found by that id");
		}
	
		return list;
		
	}


	@Override
	public Hotel viewHotelByCode(Integer hoteCode, String key) throws HotelException, LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Hotel hotel=hotelRepository.findByHotelCode(hoteCode);
		
		if(hotel==null) {
			throw new HotelException("hotel not found by that id");
		}
		
		
		return hotel;
	}


	@Override
	public List<Hotel> viewHotelByFare(Integer lowerAmount, Integer higherAmount, String key)
			throws LoginException, HotelException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<Hotel> list=hotelRepository.findByFareBetween(lowerAmount, higherAmount);
		if(list.size()==0) {
			throw new HotelException("no hotel are available");
		}
		return list;
	}


	@Override
	public String BookHotel(Integer hotelCode, String key) throws HotelException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		
		
		return null;
	}





	
}
