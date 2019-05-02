package com.frantishex.service;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frantishex.exceptions.NotFoundException;
import com.frantishex.model.Customer;
import com.frantishex.model.Merchant;
import com.frantishex.model.Sale;

@Service
@Transactional
public class SaleService {

	@PersistenceContext
	EntityManager em;

	@Autowired
	CustomerService cs;

	@Autowired
	MerchantService ms;

	public List<Sale> getAll() {
		return em.createQuery("select s from Sale s", Sale.class).getResultList();
	}

	public List<Sale> leastExpensive() {
		return em.createQuery("Select s From Sale s Where s.salePrice = (Select min (x.salePrice) from Sale x)",
				Sale.class).getResultList();
	}

	public List<Sale> getSalesByCustomerId(Long id) {
		return em.createQuery("Select s From Sale s where  s.customer.id = :customerID", Sale.class)
				.setParameter("customerID", id).getResultList();

	}

	public Sale getSaleById(Long id) {
		return em.find(Sale.class, id);

	}

	public void setTheNewPrice(Sale sale) {
		sale.setNewPrice(sale.getSalePrice()
				.subtract((sale.getDiscount().divide(new BigDecimal("100")).multiply(sale.getSalePrice()))));
	}

	public Sale getSaleByCode(Long id) {
		return getSaleById(id);
	}

	public Sale makeSaleForCustomer(Sale sale) throws NotFoundException {

		if (cs.getCustomerById(sale.getCustomer().getId()) == null) {
			throw new NotFoundException("Customer not found!");

		} else {

			Customer customer = cs.getCustomerById(sale.getCustomer().getId());

			if (customer.getTier().equals("default")) {

				sale.setDiscount(customer.getMerchant().getGlobalDiscount());
				/*
				 * customer.setDiscount(customer.getMerchant().getGlobalDiscount
				 * ());
				 */
			} else {

				sale.setDiscount(cs.getDiscountFromTier(customer));
				/* customer.setDiscount(cs.getDiscountFromTier(customer)); */

			}
			if (customer.getDiscount() != null) {
				sale.setDiscount(customer.getDiscount()); //
			}
			customer.setTurnover(cs.setTurnover(sale, customer));

			cs.makeTier(customer);

			setTheNewPrice(sale);

			sale.setCustomer(customer);

			em.persist(sale);
			return sale;
		}

	}

	/*
	 * public Sale createSale(Sale sale) {
	 * 
	 * em.persist(sale);
	 * 
	 * return sale; }
	 */
}
