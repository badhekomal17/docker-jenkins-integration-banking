package com.zensar.banking.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerLogin;
import com.zensar.banking.service.CustomerLoginService;

@RestController
public class CustomerLoginController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);

	@Autowired
	CustomerLoginService loginService;
	
	@Autowired
	BaseResponse baseResponse;

	// To change customer password
	@PostMapping("/changePassword")
	public BaseResponse changePassword(@RequestBody CustomerLogin request) {
		logger.info("Start CustomerLoginController class -> changePassword() with request");
		baseResponse = loginService.changePassword(request);
		logger.info("End CustomerLoginController class -> changePassword() with response");
		return baseResponse;
	}
	
	// To login Customer
	@PostMapping("/login")
	public BaseResponse loginCustomer(@RequestBody CustomerLogin login) {
		logger.info("Start CustomerLoginController class -> loginCustomer() with request");
		String custPassword = login.getCustomerPassword();
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String customerPassword = login.getCustomerPassword();
		// To Encode Password
		customerPassword = encoder.encodeToString(customerPassword.getBytes());
		login.setCustomerPassword(customerPassword);
		baseResponse = loginService.loginCustomer(login, custPassword);
		logger.info("End CustomerLoginController class -> loginCustomer() with response");
		return baseResponse;
	}

	// To logout Customer
	@PostMapping("/logout")
	public BaseResponse logoutCustomer(@RequestBody CustomerLogin logout) {
		logger.info("Start CustomerLoginController class -> logoutCustomer() with request");
		//Base64.Encoder encoder = Base64.getUrlEncoder();
		//String customerPassword = logout.getCustomerPassword();
		// To Encode Password
		//customerPassword = encoder.encodeToString(customerPassword.getBytes());
		//logout.setCustomerPassword(customerPassword);
		baseResponse= loginService.logoutCustomer(logout);
		logger.info("End CustomerLoginController class -> logoutCustomer() with response");
		return baseResponse;
	}

}
