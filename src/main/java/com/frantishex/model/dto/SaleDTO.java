package com.frantishex.model.dto;

import java.math.BigDecimal;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.frantishex.model.Sale;

public class SaleDTO {

	private BigDecimal salePrice;

	private BigDecimal discount;

	private BigDecimal newPrice;

	private String customer;

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public static SaleDTO entityToDTO(Sale sale) {
		ModelMapper mp = new ModelMapper();
		mp.addMappings(new PropertyMap<Sale, SaleDTO>() {
			@Override
			protected void configure() {

				map().setCustomer(source.getCustomer().getName());
				map().setSalePrice(source.getSalePrice());
				map().setDiscount(source.getDiscount());
				map().setNewPrice(source.getNewPrice());
			}
		});
		SaleDTO dto = mp.map(sale, SaleDTO.class);
		return dto;

	}

}
