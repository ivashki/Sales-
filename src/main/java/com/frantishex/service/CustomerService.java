package com.frantishex.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frantishex.model.Customer;
import com.frantishex.model.Sale;

@Service
@Transactional
public class CustomerService {

	@PersistenceContext
	EntityManager em;

	public List<Customer> getAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	public Customer getCustomerById(Long id) {
		return em.find(Customer.class, id);

	}

	public List<Customer> findWithName(String name) {
		return em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :name").setParameter("name", name)
				.getResultList();
	}

	public BigDecimal getDiscountFromTier(Customer customer) {
		if (customer.getTier().equals("bronze")) {
			return new BigDecimal(20);
		} else if (customer.getTier().equals("silver")) {
			return new BigDecimal(30);
		} else if (customer.getTier().equals("gold")) {
			return new BigDecimal(50);
		} else {
			return new BigDecimal(0);
		}
	}

	public BigDecimal setTurnover(Sale sale, Customer customer) {
		return ((sale.getSalePrice()
				.subtract((sale.getDiscount().divide(new BigDecimal("100")).multiply(sale.getSalePrice()))))
						.add(customer.getTurnover()));
	}

	public Customer createCustomer(String name) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setTurnover(customer.ZERO);
		customer.setDiscount(customer.ZERO);
		customer.setTier("default");
		em.persist(customer);
		return customer;
	}

	public void makeTier(Customer customer) {
		if (customer.getTurnover().compareTo(new BigDecimal(100)) > 0
				&& customer.getTurnover().compareTo(new BigDecimal(300)) < 0) {
			customer.setTier("bronze");
		} else if (customer.getTurnover().compareTo(new BigDecimal(300)) > 0
				&& customer.getTurnover().compareTo(new BigDecimal(500)) < 0) {
			customer.setTier("silver");
		} else if (customer.getTurnover().compareTo(new BigDecimal(500)) > 0) {
			customer.setTier("gold");
		}
	}

	public Customer updateCustomer(Customer customer, String name) {

		customer.setName(name);
		return em.merge(customer);

	}

}
