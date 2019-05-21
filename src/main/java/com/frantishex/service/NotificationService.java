package com.frantishex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.frantishex.model.Customer;
import com.frantishex.model.Sale;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationService(JavaMailSender jms) {
		this.javaMailSender = jms;
	}

	public void sendNotificationForSale(Sale sale, Customer customer) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(customer.getMerchant().getEmail());
		mail.setCc(customer.getEmail());
		// mail.setFrom("nevexisIntern@gmail.com");
		mail.setSubject("Sale");
		mail.setText("New Sale from customer with email " + customer.getEmail() + " is made. The ammount is "
				+ sale.getSalePrice() + " The ammount after discounts is " + sale.getNewPrice());

		javaMailSender.send(mail);
	}

}
