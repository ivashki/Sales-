package com.frantishex.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.frantishex.model.Merchant;

@Service
@Transactional
public class MerchantService {
	@PersistenceContext
	EntityManager em;

	public Merchant getMerchantById(Long id) {

		TypedQuery<Merchant> query = em.createQuery("SELECT m FROM Merchant m WHERE m.id=?1", Merchant.class);
		return query.setParameter(1, id).getSingleResult();

	}

	public Merchant createMerchant(Merchant merchant) {

		em.persist(merchant);
		return merchant;
	}

	public List<Merchant> getAll() {
		return em.createQuery("select m from Merchant m", Merchant.class).getResultList();
	}

}
