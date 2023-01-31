package com.masai.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BookingException;
import com.masai.Exception.LoginException;
import com.masai.Repository.BookingRepository;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.TicketRepo;
import com.masai.Service.BookingService;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Ticket;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Override
	public Ticket makeBooking(Booking booking,String key,Integer otp) throws BookingException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Bus bus=busRepository.findByBusNumber(booking.getBusNumber());
		
		booking.setBookingDate(LocalDate.now());
		
		booking.setTotal_price(booking.getQuantity()*bus.getTicketPrice());
		
		if(bus.getAvailableSeats()>=booking.getQuantity() && otp==123456) {
			bus.setAvailableSeats(bus.getAvailableSeats()-booking.getQuantity());
			busRepository.save(bus);
			
			Ticket ticket=new Ticket();
			
			ticket.setBusNumber(bus.getBusNumber());
			ticket.setDateTime(LocalDateTime.now());
			ticket.setFare(bus.getTicketPrice());
			ticket.setQuantity(booking.getQuantity());
			ticket.setRouteFrom(bus.getRoutes().getRouteFrom());
			ticket.setRouteTo(bus.getRoutes().getRouteTo());
			ticket.setTotalAmount(booking.getTotal_price());
			
			
			
		Ticket ticket2=	ticketRepo.save(ticket);
		
		ticket2.setBookingId(ticket2.getId()+"booked");
		
			booking.setTicketId(ticket2.getId());
			
			booking.setBookingId(ticket2.getId()+"booked");
		
			
			
			 Booking booking2=	bookingRepo.save(booking);
			 
			 System.out.println(ticket2);
			 
			 ticketRepo.save(ticket2);
			 
//			 ticket2.se
			 
			 	return ticket2;
		}
		
		throw new BookingException("only quantity are avaialble "+bus.getAvailableSeats());
		
	}

	@Override
	public Ticket cancelBooking(Integer id,String key) throws BookingException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
//		Optional<Booking> opt = bookingRepo.findById(id);
		Optional<Ticket> opt=ticketRepo.findById(id);
		
		
		
		if(opt.isEmpty()) {
			
			throw new BookingException("Booking is not found with the ID : "+ id + " Please provide correct ID.");
		
		}
		
		Ticket ticket = opt.get();
		
		System.out.println(ticket.getBookingId());
		
		Optional<Booking> booking=bookingRepo.findById(ticket.getBookingId());
		
		
		
		Booking booking2=booking.get();
		
		if(booking2.getBookingMobileNumber().equals(currentLoginSession.getUserMobile())) {
			
			Bus bus=busRepository.findByBusNumber(booking2.getBusNumber());
			
			
			
			bus.setAvailableSeats(bus.getAvailableSeats()+booking2.getQuantity());
			
			busRepository.save(bus);
			
			bookingRepo.delete(booking2);
			
			ticketRepo.delete(ticket);
			
			return ticket;
		}else {
			throw new LoginException("Login with ordered account");
		}
		
	}

	@Override
	public Ticket viewBooking(Integer id,String key) throws BookingException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		
		Optional<Ticket> opt=ticketRepo.findById(id);
		
		if(opt.isEmpty())
			throw new BookingException("Booking is not found with the ID : "+ id + " Please provide correct ID.");
		
		Ticket ticket = opt.get();
		
		Optional<Booking> booking=bookingRepo.findById(ticket.getBookingId());
		
		Booking b=booking.get();
		
		return ticket; 
		
//		if(b.getBookingMobileNumber().equals(currentLoginSession.getUserMobile()) || currentLoginSession.getUserType().equals("ADMIN")) {
//			return b;
//		}else {
//			throw new LoginException("Login with ordered account");
//		}
//		
		
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
