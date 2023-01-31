package com.masai.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hotelID;

	@NotNull
	@Size(min = 1, message = "Hotel name cannot be null")
    private String hotelName;
	
	@NotNull
    private String hotelType;
	
	@Pattern(regexp = "[0-9]{4}")
    private String hotelCode;
    
	@NotNull
    private String address;
	
	
    
	@NotNull(message = "Price should not be negative value")
    private Integer fare;
    


}
