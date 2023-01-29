package com.masai.ServiceImpl;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.Repository.BookingRepository;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.HotelRepository;
import com.masai.Repository.PackageRepository;
import com.masai.Repository.RouteRepository;
import com.masai.Service.CustomerService;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Customer;
import com.masai.model.Hotel;
import com.masai.model.LoginDTO;
import com.masai.model.PackageModule;
import com.masai.model.Routes;



@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired 
	private BusRepository busRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	
	
	@Override
	public Customer Ragistration(Customer customer) throws CustomerException {
		Customer customer2=customerRepo.findByCustomerMobile(customer.getCustomerMobile());
		if(customer2==null) {
			return customerRepo.save(customer);
		}
		else {
			throw new CustomerException("customer already exist by that number");
		}
	}


	@Override
	public Customer getCustomerDetailsById(Integer id,String key) throws CustomerException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		return customerRepo.findById(id).orElseThrow(()->new CustomerException("Customer not available by that id: "+id));
	}


	@Override
	public String deleteByMobileAndPassword(LoginDTO loginDTO,String key) throws LoginException, CustomerException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Customer customer=customerRepo.findByCustomerMobile(loginDTO.getMobile());
		if(customer==null) {
			throw new CustomerException("You have enter wrong number");
		}
		
		else {
			if(customer.getCustomerPassword().equals(loginDTO.getPassword())){
				customerRepo.delete(customer);
				return "customer have been delete successfully";
			}
			else {
				throw new CustomerException("you have enter wrong password");
			}
		}
		
	}

	@Override
	public String changePassword(String oldPassword, String newPassword, String key)
			throws LoginException, CustomerException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Customer customer= customerRepo.findByCustomerMobile(currentLoginSession.getUserMobile());
		
		if(customer.getCustomerPassword().equals(oldPassword)) {
			customer.setCustomerPassword(newPassword);
			customerRepo.save(customer);

			return "Password successfully changed";
		}
		throw new CustomerException("wrong password");
	}




	
	


	@Override
	public String BookTicket(Integer quantity, String key,String BusNumber) throws LoginException, BusException {
	
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Bus bus=busRepository.findByBusNumber(BusNumber);
		
		if(bus==null) {
			throw new BusException("Wrong Bus Number");
		}
		
		
		if(bus.getCapacity()>=quantity && quantity>0) {
			
			Integer newCapacity=bus.getCapacity()-quantity;
			bus.setCapacity(newCapacity);
			busRepository.save(bus);
			Booking booking=new Booking();
			booking.setBookingDate(LocalDate.now());
			booking.setBookingMobileNumber(currentLoginSession.getUserMobile());
			
			bookingRepository.save(booking);
			
			return "Booking done successfully";
			
		}
		
		return "Not enough ticket are there only ticker are there: "+bus.getCapacity();
		
	}

//*************************************************************Hotel*********************************************************************	

//	@Override
//	public List<Hotel> viewAllHotelByAddress(String address, String key) throws HotelException, LoginException {
//		
//		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//		if(currentLoginSession==null) {
//			throw new LoginException("You have to login first");
//		}
//		
//		List<Hotel> list=hotelRepository.findByAddress(address);
//		
//		if(list==null) {
//			throw new HotelException("hotel not found by that id");
//		}
//	
//		return list;
//		
//	}
//
//
//	@Override
//	public Hotel viewHotelByCode(Integer hoteCode, String key) throws HotelException, LoginException {
//		
//		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//		if(currentLoginSession==null) {
//			throw new LoginException("You have to login first");
//		}
//		
//		Hotel hotel=hotelRepository.findByHotelCode(hoteCode);
//		
//		if(hotel==null) {
//			throw new HotelException("hotel not found by that id");
//		}
//		
//		
//		return hotel;
//	}
//
//
//	@Override
//	public List<Hotel> viewHotelByFare(Integer lowerAmount, Integer higherAmount, String key)
//			throws LoginException, HotelException {
//		
//		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//		if(currentLoginSession==null) {
//			throw new LoginException("You have to login first");
//		}
//		
//		List<Hotel> list=hotelRepository.findByFareBetween(lowerAmount, higherAmount);
//		
//		if(list==null) {
//			throw new HotelException("no hotel are available");
//		}
//		
//		
//		return list;
//	}
//
//
//	@Override
//	public String BookHotel(Integer hotelCode, String key) throws HotelException, LoginException {
//		// TODO Auto-generated method stub
//		
//		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//		if(currentLoginSession==null) {
//			throw new LoginException("You have to login first");
//		}
//		
//		
//		
//		return null;
//	}
//
//
//	@Override
//	public Hotel viewHotelById(Integer id, String key) throws HotelException, LoginException {
//		
//	     CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//			
//			if(currentLoginSession==null) {
//				throw new LoginException("You have to login first");
//			}
//			
//			Optional<Hotel> opt=hotelRepository.findById(id);
//			
//			if(opt.isPresent()) {
//				return opt.get();	
//			}
//			throw new HotelException("no hotel available by that id");
//	}
//
//
//	@Override
//	public List<Hotel> viewAllHotel(String key) throws HotelException, LoginException {
//		 CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//			
//			if(currentLoginSession==null) {
//				throw new LoginException("You have to login first");
//			}
//		
//			List<Hotel> list=hotelRepository.findAll();
//			if(list==null) {
//				throw new HotelException("no hotels are available");
//			}
//			else
//			{
//				return list;
//			}
//	}
	
}











