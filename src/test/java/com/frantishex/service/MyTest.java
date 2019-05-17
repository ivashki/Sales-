package com.frantishex.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.frantishex.model.Customer;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class MyTest {
	@Autowired
	private TestEntityManager em;

	@Autowired
	private CustomerService cs;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
		Customer c = new Customer();
		c.setId(1l);
		em.persist(c);
		em.flush();

		// when
		Customer found = cs.getCustomerById(c.getId());

		// then
		Assert.assertEquals(found.getId(), c.getId());
	}

}
