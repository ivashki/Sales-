package com.frantishex.service;

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

	public Sale createSale(Sale sale) throws NotFoundException {

		Sale bate = ss.makeSale(sale);
		return bate;

	}

	public void createCustomer(Customer customer) {
		cs.createCustomer(customer);
	}

	public void createMerchant(Merchant merchant) {
		ms.createMerchant(merchant);
	}

	public List<Customer> getByName(String name) {
		return cs.getByName(name)/* .get(0) */;
	}

	public List<Customer> getAllCustomers() {
		return cs.getAll();
	}

	public Customer getCustomerById(Long id) {
		return cs.getCustomerById(id);
	}

	public List<Merchant> getAllMerchants() {
		return ms.getAll();
	}

}
