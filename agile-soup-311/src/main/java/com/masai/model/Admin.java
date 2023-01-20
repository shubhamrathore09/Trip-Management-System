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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	@NotNull
	private String adminName;
	@NotNull
	@Email
	private String adminEmail;
	@Size(min = 6,message = "password length should be more then 6")
	private String adminPassword;
	
	@Pattern(regexp = "[6789][0-9]{9}")
	private String adminMobile;
}
