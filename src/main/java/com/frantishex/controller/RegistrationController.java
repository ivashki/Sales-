package com.frantishex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frantishex.model.User;
import com.frantishex.service.NotificationService;

@RestController
public class RegistrationController {

	@Autowired
	private NotificationService ns;

	@RequestMapping("/signup")
	public String signup() {
		return "Please sign up for our service.";
	}

	@RequestMapping("/signup-success")
	public String signupSuccess() {

		User user = new User();
		user.setName("Baaago");
		user.setAge(16);
		user.setEmail("iv_velk@abv.bg");

		ns.sendNotification(user);

		return "Please sign up for our service.";
	}

}