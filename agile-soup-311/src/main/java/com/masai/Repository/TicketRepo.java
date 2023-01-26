package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer>{
	
	public Ticket findByBookingId(Integer bookingId);
	
}
