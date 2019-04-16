package com.frantishex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frantishex.model.Customer;
import com.frantishex.service.CustomerService;

@Controller // this is a spring mvc controller
@RequestMapping(path = "/customer") // we get the actual map of the root
public class CustomerController {

	@Autowired
	CustomerService cs;

	@RequestMapping(path = "/customers", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(cs.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Customer>> findWithName(@PathVariable(value = "name") String name) {

		return new ResponseEntity<List<Customer>>(cs.findWithName(name), HttpStatus.OK);

	}

	@RequestMapping(value = "/createCustomer/{name}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, String name) {
		try {
			return new ResponseEntity<Customer>(cs.createCustomer(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(cs.createCustomer(name), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}