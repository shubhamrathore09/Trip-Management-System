package com.masai.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	private String busType;
	private String busNumber;
	private Integer capacity;
	private String arivelTime;
	private String deptureTime;
	private Integer availableSeats;
	private LocalDate doj;
	
	@JsonIgnore
	@ManyToOne(cascade =   CascadeType.ALL)
	private Routes routes;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Travels travels;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		return Objects.equals(busId, other.busId) && Objects.equals(busNumber, other.busNumber);
	}


	@Override
	public int hashCode() {
		return Objects.hash(busId, busNumber);
	}
	
	
	
}
