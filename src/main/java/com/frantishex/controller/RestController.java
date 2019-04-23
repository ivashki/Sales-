package com.frantishex.controller;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.frantishex.exceptions.NotFoundException;
import com.frantishex.model.Customer;
import com.frantishex.model.Merchant;

import com.frantishex.model.dto.CustomerDTO;
import com.frantishex.model.dto.MerchantDTO;
import com.frantishex.model.dto.SaleDTO;

import com.frantishex.service.ServiceFacade;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private ServiceFacade sf;

	/*
	 * @RequestMapping(value = "/makeSale", method = RequestMethod.POST) public
	 * ResponseEntity<String> makeSale(@RequestBody Sale sale, Long id) throws
	 * NotFoundException { Customer customer = sf.getCustomerById(id);
	 * sf.createSale(sale, customer); return new
	 * ResponseEntity<String>("Sale made succesfully.", HttpStatus.OK);
	 * 
	 * }
	 */

	@RequestMapping(value = "/createMerchant", method = RequestMethod.POST)
	public ResponseEntity<String> createMerchant(@RequestBody MerchantDTO merchantDTO) {
		sf.createMerchant(merchantDTO.convertToMerchant());
		return new ResponseEntity<String>("Merchant added succesfully.", HttpStatus.OK);
	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
		sf.createCustomer(customerDTO.convertToCustomer());
		return new ResponseEntity<String>("Buyer added succesfully.", HttpStatus.OK);
	}

	@RequestMapping(value = "/createSale", method = RequestMethod.POST)
	public ResponseEntity<String> createSale(@RequestBody SaleDTO saleDTO) throws NotFoundException {
		sf.createSale(saleDTO.convertToSale());
		return new ResponseEntity<String>("Sale made succesfully.", HttpStatus.OK);
	}

	@RequestMapping(value = "/getCustomerByName", method = RequestMethod.POST)
	public ResponseEntity<List<Customer>> getCustomerByName(@RequestBody String name) {
		return new ResponseEntity<List<Customer>>(sf.getByName(name), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllMerchants", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Merchant>> getAllMerchants() {
		return new ResponseEntity<>(sf.getAllMerchants(), HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(sf.getAllCustomers(), HttpStatus.OK);

	}

}
