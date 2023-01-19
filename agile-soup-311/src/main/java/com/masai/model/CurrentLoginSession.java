package com.masai.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentLoginSession {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sId;

	private String userMobile;
	
	private String userKey;
}
