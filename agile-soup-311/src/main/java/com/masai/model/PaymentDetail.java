package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PaymentDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	private Double totalPayment;
	private Double paidPayment;
	private String paymentStatus;
}
