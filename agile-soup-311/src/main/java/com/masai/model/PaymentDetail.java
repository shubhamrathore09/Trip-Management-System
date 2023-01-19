package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetail {
	private Integer paymentId;
	private Double totalPayment;
	private Double paidPayment;
	private String paymentStatus;
}
