package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	@Id
	private Integer id;
	private String BusNumber;
	private Double fare;
	private Integer quantity;
	private Double totalAmount=fare*quantity;
	private String routeFrom;
	private String routeTo;
	
}
