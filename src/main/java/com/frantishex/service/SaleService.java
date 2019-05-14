package com.frantishex.service;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.frantishex.exceptions.NotFoundException;
import com.frantishex.model.Customer;

import com.frantishex.model.Sale;

@Service
@Transactional
public class SaleService extends GenericService<Sale, Long> {

	@PersistenceContext
	EntityManager em;

	@Autowired
	CustomerService cs;

	@Autowired
	MerchantService ms;

	@Autowired
	NotificationService ns;

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

	public void setThePoints(Sale sale, Customer customer) {
		sale.setPoints(sale.getNewPrice().multiply(customer.getMerchant().getScale()));

		customer.setPointsForDiscount(sale.getPoints().add(customer.getPointsForDiscount()));
	}

	public void setTheCustomer(Sale sale, Customer customer) {
		sale.setCustomer(customer);
	}

	public void setTheTurnover(Sale sale, Customer customer) {
		customer.setTurnover(cs.setTurnover(sale, customer));
	}

	public void SaleSetter(Sale sale, Customer customer) {
		if (customer.getTier().equals("default")) {

			sale.setDiscount(customer.getMerchant().getGlobalDiscount());

		} else {

			sale.setDiscount(cs.getDiscountFromTier(customer));
			customer.setTierDiscount(cs.getDiscountFromTier(customer));

		}
		if (customer.getSpecificDiscount().compareTo(new BigDecimal("0")) > 0) {
			sale.setDiscount(customer.getSpecificDiscount());
		}
	}

	public Sale makeSaleForCustomer(Sale sale) throws NotFoundException {

		if (cs.getCustomerById(sale.getCustomer().getId()) == null) {
			throw new NotFoundException("Customer not found!");

		} else {

			Customer customer = cs.getCustomerById(sale.getCustomer().getId());

			SaleSetter(sale, customer);

			setTheTurnover(sale, customer);

			cs.makeTier(customer);

			setTheNewPrice(sale);

			setTheCustomer(sale, customer);

			setThePoints(sale, customer);

			em.persist(sale);

			ns.sendNotificationForSale(sale, customer);

			return sale;
		}

	}

	public Sale createSale(Sale sale) {

		sale.setNewPrice(sale.getSalePrice());
		sale.setCustomer(null);
		sale.setDiscount(null);
		sale.setPoints(null);
		em.persist(sale);

		return sale;
	}

}
