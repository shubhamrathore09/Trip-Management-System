package com.masai.ServiceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Repository.BookingRepository;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.RouteRepository;
import com.masai.Service.CustomerService;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.model.Routes;



@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired 
	private BusRepository busRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	

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
	public List<Routes> viewAllRoutes(String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<Routes> list=routeRepository.findAll();
		if(list==null) {
			throw new RouteException("there is no route");
		}
		return list;
	}


	@Override
	public Set<Bus> getBusBySourceAndDestincation(String routeFrom, String routeTo, String key,LocalDate date)
			throws LoginException, RouteException, BusException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		Set<Bus> set=routeRepository.getBusByRoutes(routeFrom, routeTo);
		Set<Bus> buses = new HashSet<>();
		for(Bus e : set) {
			if(e.getDoj().compareTo(date)==0) {
				buses.add(e);
			}
		}
		if(set.isEmpty()) {
			throw new RouteException("No Buses are avaialble by that route");
		}
		return buses;
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
			booking.setBookingType("Bus");
			booking.setBookingMobileNumber(currentLoginSession.getUserMobile());
			
			bookingRepository.save(booking);
			
			return "Booking done successfully";
			
		}
		
		return "Not enough ticket are there only ticker are there: "+bus.getCapacity();
		
	}
}











