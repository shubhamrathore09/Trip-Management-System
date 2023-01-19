package com.masai.model;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
	@Pattern(regexp = "[0-9]{10}")
	private String mobile;
	
	private String password;

	
	
	
}
