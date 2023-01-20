package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CurrentLoginSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
<<<<<<< HEAD
	private String mobile;
	private String key;
	private Integer userId;
=======
	
	private Integer userId;
		
	private String userMobile;
	
	private String userKey;
>>>>>>> 5bba383094b7f7f869bdc60f58c2a7c5a1f50891
}
