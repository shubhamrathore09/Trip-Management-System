package com.masai.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String BusNumber;
	
	private Double fare;
	
	private LocalDateTime dateTime;
	
	@JsonIgnore
	private String bookingId;
	
	private Integer quantity;
	
	private Double totalAmount;
	
	private String routeFrom;
	
	private String routeTo;
	

	

}