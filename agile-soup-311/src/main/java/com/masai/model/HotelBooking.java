package com.masai.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelBooking {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer id;
	
	@Pattern(regexp = "^[6789][0-9]{9}")
	private String mobileNumber;
	
	@JsonIgnore
	private String bookingNumber;
	
	@NotNull
	private String HotelCode;
	
//	@JsonIgnore
	@NotNull
	private Date checkIn;
//	@JsonIgnore
	@NotNull
	private Date CheckOut;
	
	
}
