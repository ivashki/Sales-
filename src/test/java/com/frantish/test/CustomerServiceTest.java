package com.frantish.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import com.frantishex.exceptions.NotFoundException;
import com.frantishex.model.Customer;
import com.frantishex.service.ServiceFacade;
import com.frantishex.simpleMethods.Math;

import static org.mockito.Matchers.any;

public class CustomerServiceTest {

	@Mock
	ServiceFacade sf;

	@Test
	public void doesItWork() throws NotFoundException {

		final Customer expected = new Customer();

		final Customer actual = sf.createCustomer(new Customer());

		Assert.assertEquals(actual, expected);

	}

}
