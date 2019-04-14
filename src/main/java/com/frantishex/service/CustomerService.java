package com.frantishex.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frantishex.model.Customer;

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

	public BigDecimal getDiscountFromTier(Customer customer) {
		if (customer.getTier() == "bronze") {
			return new BigDecimal(20);
		} else if (customer.getTier() == "silver") {
			return new BigDecimal(30);
		} else if (customer.getTier() == "gold") {
			return new BigDecimal(50);
		} else {
			return new BigDecimal(0);
		}
	}

	public Customer createCustomer() {
		Customer customer = new Customer();
		customer.setTurnover(customer.ZERO);
		customer.setDiscount(customer.ZERO);
		customer.setTier("default");
		em.persist(customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer, String name) {

		customer.setName(name);
		return em.merge(customer);

	}

}
