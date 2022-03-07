package com.zensar.banking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.banking.constant.Validation;
import com.zensar.banking.dao.CustomerLoginDao;
import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerLogin;
import com.zensar.banking.model.StatusData;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginServiceImpl.class);

	@Autowired
	CustomerLoginDao loginDao;

	@Autowired
	StatusData status;

	@Autowired
	BaseResponse baseResponse;
	
	@Autowired
	Validation validate;

	// For Change Password
	@Override
	public BaseResponse changePassword(CustomerLogin request) {
		logger.info("Start CustomerLoginServiceImpl class -> changePassword()");
		boolean result = false;
		
		result = validate.validateData("password", request.getNewPassword());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Customer Password!! Password must have atleast one special symbol, atleast one alphabets in capital letter and atleast one number.");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		Integer changePassword = loginDao.changePassword(request);
		if (changePassword == 1) {
			CustomerLogin customerData = loginDao.getCustomerData(request.getCustomerId());
			status.setStatusCode(1);
			status.setStatusMessage("Password Change request processed successfully!!");
			baseResponse.setData(customerData);
			baseResponse.setStatus(status);
			return baseResponse;
		} else if(changePassword == -1) {
			status.setStatusCode(-1);
			status.setStatusMessage("Unable to Change Password !! Customer status in inactive State Or Old Password is entered Incorrect!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}else {
			status.setStatusCode(-2);
			status.setStatusMessage("No such user find!! Wrong user Id Entered");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}

	// For Login Customer
	@Override
	public BaseResponse loginCustomer(CustomerLogin login, String custPassword) {
		logger.info("Start CustomerLoginServiceImpl class -> loginCustomer()");

		Integer loginData = loginDao.loginCustomer(login, custPassword);
		if (loginData == 1) {
			CustomerLogin customerData = loginDao.getCustomerData(login.getCustomerId());
			status.setStatusCode(1);
			status.setStatusMessage("Login successfully!!");
			baseResponse.setData(customerData);
			baseResponse.setStatus(status);
			return baseResponse;
		} else if(loginData == -1) {
			status.setStatusCode(-1);
			status.setStatusMessage("Unable to Login !!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}else {
			status.setStatusCode(-2);
			status.setStatusMessage("No such user find!! Wrong Data Entered!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}

	// For Logout Customer
	@Override
	public BaseResponse logoutCustomer(CustomerLogin logout) {
		logger.info("Start CustomerLoginServiceImpl class -> logoutCustomer()");
		Integer logoutData = loginDao.logoutCustomer(logout);
		if (logoutData == 1) {
			CustomerLogin customerData = loginDao.getCustomerData(logout.getCustomerId());
			status.setStatusCode(1);
			status.setStatusMessage("Logout Successfully!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		} else if(logoutData == -1) {
			status.setStatusCode(-1);
			status.setStatusMessage("Unable to Logout !!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}else {
			status.setStatusCode(-2);
			status.setStatusMessage("No such user find!! Wrong user Id Entered");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}
}
