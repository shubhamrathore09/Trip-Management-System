package com.masai.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Routes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	private String routeFrom;
	private String routeTo;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private LocalDate doj;
	private String pickupPoint;
	private Double fare;
	
}
