package com.zensar.banking.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.banking.dao.AccountDao;
import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerAccount;
import com.zensar.banking.model.CustomerTransaction;
import com.zensar.banking.model.StatusData;

@Service
public class AccountServiceImpl implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerRegisterServiceImpl.class);

	@Autowired
	AccountDao accountDao;

	@Autowired
	StatusData status;

	@Autowired
	BaseResponse baseResponse;

	@Autowired
	CustomerRegisterServiceImpl customerService;

	// Validation For Account Table
	public boolean validateData(String input, String data) {
		boolean matches = false;
		 String patternString = "[a-zA-Z]+\\.?";
		matches = Pattern.matches(patternString, data);
		return matches;
	}// end validateData()

	// To Open Customer Account
	@Override
	public BaseResponse accountOpen(CustomerAccount request) {
		logger.info("Start AccountServiceImpl class -> accountOpen()");
		boolean result = false;
		result = validateData("names", request.getCustomerAccountType().toUpperCase());
		if (result == false) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Account Type entered !! Please enter only alphabets!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		// customerRegistration
		Integer accountStatus = accountDao.accountOpen(request);
		if (accountStatus == 1) {
			CustomerAccount AccountData = accountDao.getLatestAccount();
			status.setStatusCode(1);
			status.setStatusMessage("Account Open request processed successfully");
			baseResponse.setData(AccountData);
			baseResponse.setStatus(status);
			return baseResponse;
		} else if (accountStatus == -1) {
			status.setStatusCode(-1);
			status.setStatusMessage(
					"Please Enter Correct Data!! For Saving Account customer balance more than 5000 & Current Account customer balance more than 1000!!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		} else {
			status.setStatusCode(-2);
			status.setStatusMessage("Unable to Open Account!!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}

	// To Deposit Amount
	@Override
	public BaseResponse depositAmount(CustomerAccount deposit) {
		logger.info("Start AccountServiceImpl class -> depositAmount()");
		boolean result = false;
		result = validateData("names", deposit.getCustomerAccountType());
		if (result == false) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Account Type entered ! Please enter only alphabets!!....");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
		Integer depositStatus = accountDao.depositAmount(deposit);
		if (depositStatus == 1) {
			CustomerTransaction TransactionData = accountDao.getLatestTransaction();
			status.setStatusCode(1);
			status.setStatusMessage("Deposite request processed successfully");
			baseResponse.setData(TransactionData);
			baseResponse.setStatus(status);
			return baseResponse;
		} else if (depositStatus == -1) {
			status.setStatusCode(-1);
			status.setStatusMessage("Please Enter Correct Transaction Amount!!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		} else {
			status.setStatusCode(-2);
			status.setStatusMessage("Unable to deposite Amount!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}

	// To Withdraw Amount
	@Override
	public BaseResponse withdrawAmount(CustomerAccount withdraw) {
		logger.info("Start AccountServiceImpl class -> withdrawAmount()");
		boolean result = false;
		result = validateData("names", withdraw.getCustomerAccountType());
		if (result == false) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Account Type entered ! Please enter only alphabets!!....");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
		
		Integer withdrawStatus = accountDao.withdrawAmount(withdraw);
		if (withdrawStatus == 1) {
			CustomerTransaction TransactionData = accountDao.getLatestTransaction();
			status.setStatusCode(1);
			status.setStatusMessage("Withdraw request processed successfully");
			baseResponse.setData(TransactionData);
			baseResponse.setStatus(status);
			return baseResponse;
		} else {
			status.setStatusCode(-1);
			status.setStatusMessage("Unable to Withdraw Amount!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}
}
