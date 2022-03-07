package com.zensar.banking.dao;

import java.util.List;

import com.zensar.banking.model.CustomerRegistration;

public interface CustomerRegistrationDao {

	public Integer registerCustomer(CustomerRegistration customer);
	
	public CustomerRegistration getLatestCustomer();
	
	public List<CustomerRegistration> getCustomerList(Integer start, Integer offset);
	
	public Integer updateRegisterCustomer(CustomerRegistration customer);

	public Integer deleteCustomer(int customerId);
	
	public CustomerRegistration getCustomerByIdData(int customerId); 
}
