package com.frantishex.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfiguration {

	@Bean
	@Primary
	public CustomerService getCustomerService() throws Exception {
		CustomerService newObject = Mockito.spy(new CustomerService());
		Mockito.doThrow(new Exception("proba exception")).when(newObject).create(Mockito.any());
		return newObject;
	}

	@Bean
	@Primary
	public MerchantService getMerchantService() throws Exception {
		MerchantService newObject = Mockito.spy(new MerchantService());
		Mockito.doThrow(new Exception("proba exception")).when(newObject).create(Mockito.any());
		return newObject;
	}

}
