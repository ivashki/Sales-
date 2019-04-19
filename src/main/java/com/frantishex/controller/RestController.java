package com.frantishex.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.frantishex.exceptions.NotFoundException;
import com.frantishex.model.Customer;
import com.frantishex.model.Sale;
import com.frantishex.model.dto.CustomerDTO;
import com.frantishex.model.dto.SaleDTO;
import com.frantishex.model.dto.SaleReturnDTO;
import com.frantishex.service.ServiceFacade;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private ServiceFacade sf;

	/*
	 * @RequestMapping(value = "/createSale", method = RequestMethod.POST)
	 * public ResponseEntity<SaleReturnDTO> createSale(@RequestBody SaleDTO
	 * saleDTO,@RequestBody CustomerDTO customerDTO) throws NotFoundException {
	 * 
	 * Sale sale = sf.createSale(saleDTO.convertToSale(),
	 * customerDTO.convertToCustomer()); return new
	 * ResponseEntity<SaleReturnDTO>(SaleReturnDTO.convertToDTO(sale),
	 * HttpStatus.OK);
	 * 
	 * }
	 */

	@RequestMapping(value = "/makeSale", method = RequestMethod.POST)
	public ResponseEntity<String> makeSale(@RequestBody Sale sale, Long id) throws NotFoundException {
		Customer customer = sf.getCustomerById(id);
		sf.createSale(sale, customer);
		return new ResponseEntity<String>("Sale made succesfully.", HttpStatus.OK);

	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
		sf.CreateCustomer(customerDTO.convertToCustomer());
		return new ResponseEntity<String>("Buyer added succesfully.", HttpStatus.OK);
	}

	@RequestMapping(value = "/getCustomerByName", method = RequestMethod.GET)
	public ResponseEntity<Customer> getBuyerByName(@RequestBody String name) {
		return new ResponseEntity<Customer>(sf.getByName(name), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(sf.getAllCustomers(), HttpStatus.OK);

	}

}
