package com.zensar.banking.service;

import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerLogin;

public interface CustomerLoginService {
	
	public BaseResponse changePassword(CustomerLogin request);
	
	public BaseResponse loginCustomer(CustomerLogin login, String custPassword);
	
	public BaseResponse logoutCustomer(CustomerLogin logout);
}
