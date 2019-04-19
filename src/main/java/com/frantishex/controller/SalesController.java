/*package com.frantishex.controller;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frantishex.model.Customer;
import com.frantishex.model.Sale;
import com.frantishex.model.SaleFacade;
import com.frantishex.model.dto.SaleDTO;
import com.frantishex.service.CustomerService;
import com.frantishex.service.SaleService;

@Controller // this is a spring mvc controller
@RequestMapping(path = "/sales") // we get the actual map of the root
public class SalesController {

	@Autowired
	SaleService saleService;

	@Autowired
	CustomerService cs;

	
	 * @Autowired private SaleFacade sf;
	 

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Sale>> getAllSales() {
		return new ResponseEntity<>(saleService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/mostExpensiveSale", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Sale>> getMostExpensiveSale() {
		return new ResponseEntity<List<Sale>>(saleService.mostExpensive(), HttpStatus.OK);
	}

	@RequestMapping(path = "/leastExpensiveSale", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Sale>> getLeastExpensiveSale() {
		return new ResponseEntity<List<Sale>>(saleService.leastExpensive(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Sale> getSaleById(@RequestParam Long id) {
		try {
			return new ResponseEntity<Sale>(saleService.getSaleById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Sale>(saleService.getSaleById(id), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/makeSale/{id}", method = RequestMethod.POST)
	public void makeSale(@RequestBody Sale sale, @PathVariable Long id) {
		Customer customer = cs.getCustomerById(id);
		saleService.makeSale(sale, customer);

	}

	
	 * @RequestMapping(value = "/SALES/{id}", method = RequestMethod.GET)
	 * public @ResponseBody ResponseEntity<SaleDTO> getSale(@PathVariable("id")
	 * Long id) { SaleDTO sale = sf.getSaleById(id); return new
	 * ResponseEntity<SaleDTO>(sale, HttpStatus.OK); }
	 

	// It is not ready yet

	@RequestMapping(value = "/getSalesForCustomer/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getAll(@PathVariable Long id) {

		List<SaleDTO> dtos = new ArrayList<>();
		List<Sale> sales = saleService.getSalesByCustomerId(id);
		for (Sale sale : sales) {
			dtos.add(SaleDTO.entityToDTO(sale));
		}
		return new ResponseEntity<Object>(dtos, HttpStatus.OK);

	}

}*/
