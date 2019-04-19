package com.frantishex.model.dto;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.frantishex.model.Customer;
import com.frantishex.model.Sale;

public class SaleReturnDTO {

	private String name;
	private BigDecimal price;
	private BigDecimal discount;
	private BigDecimal discountedPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public static SaleReturnDTO convertToDTO(Sale sale) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.addMappings(new PropertyMap<Sale, SaleReturnDTO>() {
			@Override
			protected void configure() {
				map().setName(source.getCustomer().getName());
			}
		});

		return modelMapper.map(sale, SaleReturnDTO.class);
	}

}