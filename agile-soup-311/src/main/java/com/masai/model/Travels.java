package com.masai.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Travels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer travelsId;
	
	private String travelsName;

	private String address;
	
	private String contact;
	
	@JsonIgnore
	@OneToMany(mappedBy = "travels",cascade = CascadeType.ALL)
	private Set<Bus> bus = new HashSet<>();
	
}
