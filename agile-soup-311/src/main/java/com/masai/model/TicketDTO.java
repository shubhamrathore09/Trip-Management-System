package com.masai.model;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	private String BusNumber;
	private Integer quantity;
	private LocalDate doj;
}
