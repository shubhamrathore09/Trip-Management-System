package com.masai.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@NotNull
@ToString
public class RouteDTO {
	private String routeFrom;
	private String routeTo;
}
