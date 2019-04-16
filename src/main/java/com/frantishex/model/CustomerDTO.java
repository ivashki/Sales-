package com.frantishex.model;

import java.math.BigDecimal;

public class CustomerDTO {

	private String name;
	private BigDecimal turnover;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

}
