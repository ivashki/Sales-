package com.frantishex.model.dto;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.frantishex.model.Customer;
import com.frantishex.model.Sale;

public class SaleDTO {
	private Long id;
	private BigDecimal salePrice;
	private String customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public static SaleDTO entityToDTO(Sale sale) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Sale, SaleDTO>() {
			@Override
			protected void configure() {
				map().setCustomer(source.getCustomer().getName());
				map().setId(source.getCustomer().getId());
				map().setSalePrice(source.getSalePrice());
			}

		});
		SaleDTO dto = modelMapper.map(sale, SaleDTO.class);
		return dto;
	}
}