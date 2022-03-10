package com.zensar.banking.controller;

//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerAccount;
import com.zensar.banking.service.AccountService;

@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRegisterController.class);

	@Autowired
	AccountService accountService;
	@Autowired
	BaseResponse baseResponse;
	
	@RequestMapping("/hello")
	public String hello() 
	{
		return "Hello javaTpoint";
	}

	// To Open Customer Account
	@PostMapping("/accountCreation")
	public BaseResponse accountOpen(@RequestBody CustomerAccount request) {
		logger.info("Start AccountController class -> accountOpen() with request");
		baseResponse = accountService.accountOpen(request);
		logger.info("END AccountController class -> accountOpen() with response");
		return baseResponse;
	}

	// To deposit amount
	@PostMapping("/deposit")
	public BaseResponse depositeAmount(@RequestBody CustomerAccount deposit) {
		System.err.println(deposit);
		logger.info("Start AccountController class -> depositeAmount() with request");
		baseResponse = accountService.depositAmount(deposit);
		logger.info("END AccountController class -> depositeAmount() with response");
		return baseResponse;
	}

	// To withdraw amount
	@PostMapping("/withdraw")
	public BaseResponse withdrawAmount(@RequestBody CustomerAccount withdraw) {
		logger.info("Start AccountController class -> withdrawAmount() with request");
		baseResponse = accountService.withdrawAmount(withdraw);
		logger.info("END AccountController class -> withdrawAmount() with response");
		return baseResponse;
	}
}
