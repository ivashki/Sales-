package com.frantishex.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.frantishex.model.Sale;

public class SaleDTO2 {

	@Autowired
	private ModelMapper modelMapper = new ModelMapper();

	@NotNull
	private BigDecimal salePrice;

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Sale convertToSale() {
		Sale sale = modelMapper.map(this, Sale.class);

		return sale;
	}
}
