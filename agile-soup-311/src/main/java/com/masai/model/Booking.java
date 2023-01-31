package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private String bookingId;

	@JsonIgnore
	private LocalDate bookingDate;
	
	private String BookingMobileNumber;
	
	private String BusNumber;
	
	private Integer quantity;
	
	@JsonIgnore
	private Double total_price;
	
	
	@JsonIgnore
	private Integer ticketId;
	
	
}
