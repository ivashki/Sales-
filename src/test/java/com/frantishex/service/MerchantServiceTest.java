package com.frantishex.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.frantishex.model.Merchant;

public class MerchantServiceTest {

	@InjectMocks
	MerchantService ms;

	@Mock
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMerchantById() {

		Merchant m = new Merchant();
		m.setId(1L);
		m.setEmail("gagarin.com");
		m.setGlobalDiscount(new BigDecimal(16));
		m.setScale(new BigDecimal(16));
		m.setName("yury");

		when(em.find(anyObject(), anyLong()));

		Merchant m2 = ms.getMerchantById(1L);

		assertNotNull(m2);

		assertEquals("yury", m2.getName());
	}

}
