package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(unique = true)
	private String customerEmail;

	@NotNull
	@Size(min = 6,message = "password length should be more then 6")

	
	@Size(min = 6,message = "password length should be more than 6")

	private String customerPassword;
	@NotNull
	@Pattern(regexp = "[6789][0-9]{9}")
	private String customerMobile;
	
	@JsonIgnore
	private String userType = "User";
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Feedback> feedbacks = new ArrayList<>();
}
