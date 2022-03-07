package com.zensar.banking.service;

import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerRegistration;

public interface CustomerRegisterService {

	public BaseResponse registerCustomer(CustomerRegistration customer);
	
	public BaseResponse getCustomerList(Integer start, Integer offset);
	
	public BaseResponse updateRegisterCustomer(CustomerRegistration customer ); 
	
	public BaseResponse deleteRegisterCustomer(int customerId);
	
	public BaseResponse getCustomerByIdData(String id); 
}
