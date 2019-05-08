package com.frantishex.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frantishex.exceptions.NotFoundException;

import com.frantishex.model.Customer;
import com.frantishex.model.Merchant;
import com.frantishex.model.Sale;

@Service
@Transactional
public class ServiceFacade {

	@Autowired
	SaleService ss;

	@Autowired
	CustomerService cs;

	@Autowired
	MerchantService ms;

	@PersistenceContext
	EntityManager em;

	public Sale createSaleForCustomer(Sale sale) throws NotFoundException {

		Sale bate = ss.makeSaleForCustomer(sale);
		return bate;

	}

	public Sale createSale(Sale sale) throws NotFoundException {

		Sale bate = ss.createSale(sale);
		return bate;

	}

	public Customer createCustomer(Customer customer) throws NotFoundException {
		Customer bate = cs.createCustomer(customer);
		return bate;
	}

	public void createMerchant(Merchant merchant) {
		ms.createMerchant(merchant);
	}

	public List<Customer> getByName(String name) {
		return cs.getByName(name);
	}

	public List<Customer> getAllCustomers() {
		return cs.getAll();
	}

	public Customer getCustomerById(Long id) {
		return cs.getCustomerById(id);
	}

	public Customer getCustomercheById(Long id) {
		return cs.findById(id);
	} //this method is using generic service

	public List<Merchant> getAllMerchants() {
		return ms.getAll();
	}

	public void updateGlobalDiscountForMerchant(Long id, BigDecimal value) {
		ms.updateGlobalDiscount(id, value);

	}

	public void updatelDiscountForCustomer(Long id, BigDecimal value) {
		cs.updateDiscount(id, value);
	}

	public void updateScaleForMerchant(Long id, BigDecimal value) {
		ms.updateScale(id, value);

	}

}
