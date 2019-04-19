package com.frantishex.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.frantishex.model.Sale;

public class SaleDTO {

	@Autowired
	private ModelMapper modelMapper = new ModelMapper();

	@NotNull
	private Long customerId;

	@NotNull
	private BigDecimal price;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Sale convertToSale() {
		Sale sale = modelMapper.map(this, Sale.class);
		return sale;
	}
}
