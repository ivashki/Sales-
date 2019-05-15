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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.frantishex.model.Customer;
import com.frantishex.model.Merchant;

public class MerchantServiceTest {

	@Mock
	CustomerService cs;

	@InjectMocks
	ServiceFacade sf;

	// shoild make Test for discount with help from tutorial
	// :https://javacodehouse.com/blog/mockito-tutorial/?fbclid=IwAR3xS_eY_X9LPetWp0tsVtcbtiZL6L96sY8hd660apG7l6li7R50FcG7PHs

}
