package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@NotNull
	private String customerName;
	@Email
	@NotNull
	private String customerEmail;
	@NotNull
	@Size(min = 6,message = "password length should be more then 6")
	private String customerPassword;
	@NotNull
	@Pattern(regexp = "[6789][0-9]{9}")
	private String customerMobile;
}
