package com.frantishex.model.dto;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.frantishex.model.Customer;

public class CustomerDTO {

	@Autowired
	private static ModelMapper modelMapper = new ModelMapper();

	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer convertToCustomer() {
		Customer customer = modelMapper.map(this, Customer.class);
		return customer;
	}

}
