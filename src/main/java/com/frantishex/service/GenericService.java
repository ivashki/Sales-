package com.frantishex.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class GenericService<T, K> {
	@PersistenceContext
	private EntityManager em;

	private Class<T> type;

	public GenericService() {
	}

	public GenericService(Class<T> type) {
		this.type = type;
	}

	public void create(T entity) throws Exception{
		em.persist(entity);
	}

	public void update(T entity) {
		em.merge(entity);
	}

	public void remove(T entity) {
		em.remove(entity);
	}

	public T findById(K id) {
		return em.find(type, id);
	}
}
