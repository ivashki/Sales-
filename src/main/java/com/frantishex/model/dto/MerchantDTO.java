package com.frantishex.model.dto;

import javax.validation.constraints.NotNull;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.frantishex.model.Merchant;

public class MerchantDTO {

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

	public Merchant convertToMerchant() {
		Merchant merchant = modelMapper.map(this, Merchant.class);
		return merchant;
	}

}
