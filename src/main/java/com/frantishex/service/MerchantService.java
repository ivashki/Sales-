package com.frantishex.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class MerchantService {
	@PersistenceContext
	EntityManager em;
}
