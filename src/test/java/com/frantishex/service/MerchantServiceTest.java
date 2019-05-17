package com.frantishex.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.frantishex.model.Merchant;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional()
public class MerchantServiceTest {

	@Autowired
	MerchantService merchantService;

	@Test(expected = Exception.class)
	public void test1() throws Exception {
		Merchant m = new Merchant();
		m.setName("ivan");
		merchantService.create(m);
	}

	@Test(expected = Exception.class)
	public void test2() throws Exception {
		Merchant m = new Merchant();
		m.setName("ivan");
		merchantService.create(m);
	}

}
