package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(min = 6,message = "password length should be more then 6")
	private String customerPassword;
	
	@Pattern(regexp = "[6789][0-9]{9}")
	private String customerMobile;
	
}
