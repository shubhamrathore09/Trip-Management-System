package com.masai.model;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PackageModule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer packageId;
	
	@NotNull
	private String packageName;
	@NotNull
	private String packageDescription;
	@NotNull
	private String packageType;
	@Min(value = 6000, message = "Minimum packages are start from 6000")
	private Double packageCost;
	
	private Integer hotelId;
	private Integer busId;
}
