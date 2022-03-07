package com.zensar.banking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerRegistration;
import com.zensar.banking.model.RequestBodyDO;
import com.zensar.banking.service.CustomerLoginService;
import com.zensar.banking.service.CustomerRegisterService;

@RestController
public class CustomerRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRegisterController.class);

	@Autowired
	CustomerRegisterService registerService;
	@Autowired
	CustomerLoginService loginService;
	@Autowired
	BaseResponse baseResponse;

	// To save new Customer
	@PostMapping("/saveCustomer")
	public BaseResponse registerCustomer(@RequestBody CustomerRegistration customer) {
		logger.info("Start CustomerRegisterController class -> registerCustomer() with request");
		baseResponse = registerService.registerCustomer(customer);
		logger.info("END CustomerRegisterController class -> registerCustomer() with response");
		return baseResponse;
	}

	// To get all Customer from database
	@PostMapping("/getAllCustomerList")
	public BaseResponse getCustomerList(@RequestBody RequestBodyDO requestBody) {
		logger.info("Start CustomerRegisterController class -> getCustomerList() with request");
		Integer start = requestBody.getStart();
		Integer offset = requestBody.getOffset();
		baseResponse = registerService.getCustomerList(start, offset);
		logger.info("End CustomerRegisterController class -> getCustomerList() with request");
		return baseResponse;
	}

	// Get Customer Details By Id using @RequestBody
	@PostMapping("/getCustomerByIdData")
	public BaseResponse getCustomerByIdData(@RequestBody CustomerRegistration customer) {
		logger.info("Start CustomerRegisterController class -> getCustomerByIdData() with request");
		String id = customer.getId();
		baseResponse = registerService.getCustomerByIdData(id);
		logger.info("End CustomerRegisterController class -> getCustomerByIdData() with request");
		return baseResponse;
	}

	// To update Customer in database
	@PostMapping("/updateCustomer")
	public BaseResponse updateRegisterCustomer(@RequestBody CustomerRegistration customer) {
		logger.info("Start CustomerRegisterController class -> updateRegisterCustomer() with request");
		baseResponse = registerService.updateRegisterCustomer(customer);
		logger.info("End CustomerRegisterController class -> updateRegisterCustomer() with request");
		return baseResponse;
	}

	// To delete Customer from database
	@PostMapping("/deleteCustomer")
	public BaseResponse deleteRegisterCustomer(@RequestBody CustomerRegistration customer) {
		logger.info("Start CustomerRegisterController class -> deleteRegisterCustomer() with request");
		baseResponse = registerService.deleteRegisterCustomer(customer.getCustomerId());
		logger.info("End CustomerRegisterController class -> deleteRegisterCustomer() with request");
		return baseResponse;
	}
}
