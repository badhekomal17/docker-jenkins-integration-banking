package com.zensar.banking.service;

import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerAccount;

public interface AccountService {
	
	public BaseResponse accountOpen(CustomerAccount request);
	
	public BaseResponse depositAmount(CustomerAccount deposit);
	
	public BaseResponse withdrawAmount(CustomerAccount withdraw);
}
