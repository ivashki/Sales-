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
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(cs.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long id) {

		return new ResponseEntity<Customer>(cs.getCustomerById(id), HttpStatus.OK);

	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<Customer>(cs.createCustomer(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(cs.createCustomer(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/updateCustomer/{name}/{id}", method = RequestMethod.PUT)

	public ResponseEntity<Customer> updateCustomer(@RequestBody String name, @PathVariable Long id) {
		Customer customer = cs.getCustomerById(id);
		return new ResponseEntity<Customer>(cs.updateCustomer(customer, name), HttpStatus.OK);

	}

}