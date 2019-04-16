package com.frantishex.model;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.frantishex.model.dto.SaleDTO;
import com.frantishex.service.SaleService;

public class SaleFacade {

	@Autowired
	private SaleService ss;

	@Autowired
	private ModelMapper modelMapper;

	public SaleDTO getSaleById(Long id) {
		return convertToSaleDto(ss.getSaleById(id));
	}

	private SaleDTO convertToSaleDto(Sale sale) {
		SaleDTO saleDTO = modelMapper.map(sale, SaleDTO.class);
		return saleDTO;
	}

}
