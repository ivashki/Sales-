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
public class SaleService {

	@PersistenceContext
	EntityManager em;

	@Autowired
	CustomerService cs;

	public List<Sale> getAll() {
		return em.createQuery("select s from Sale s", Sale.class).getResultList();
	}

	public List<Sale> mostExpensive() {
		return em.createQuery("Select s From Sale s Where s.salePrice = (Select max (x.salePrice) from Sale x)",
				Sale.class).getResultList();
	}

	public List<Sale> leastExpensive() {
		return em.createQuery("Select s From Sale s Where s.salePrice = (Select min (x.salePrice) from Sale x)",
				Sale.class).getResultList();
	}

	public List<Sale> getSalesByCustomerId(Long id) {
		return em.createQuery("Select s From Sale s where  s.customer.id = :customerID", Sale.class)
				.setParameter("customerID", id).getResultList();

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

	public Sale getSaleById(Long id) {
		return em.find(Sale.class, id);

	}

	public void makeSale(Sale sale, Customer customer) {

		customer.setDiscount(cs.getDiscountFromTier(customer));

		sale.setDiscount(cs.getDiscountFromTier(customer));

		customer.setTurnover((sale.getSalePrice()
				.subtract((sale.getDiscount().divide(new BigDecimal("100")).multiply(sale.getSalePrice()))))
						.add(customer.getTurnover()));

		makeTier(customer);

		sale.setNewPrice(sale.getSalePrice()
				.subtract((sale.getDiscount().divide(new BigDecimal("100")).multiply(sale.getSalePrice()))));
		sale.setCustomer(customer);
		em.persist(sale);
	}
	//// i have put some comments here 

}
