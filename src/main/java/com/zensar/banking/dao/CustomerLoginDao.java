package com.zensar.banking.dao;

import com.zensar.banking.model.CustomerLogin;

public interface CustomerLoginDao {
	
	public Integer changePassword(CustomerLogin request);
	
	public Integer loginCustomer(CustomerLogin login, String custPassword);
	
	//To fetch response id,email,password for login & change password
	public CustomerLogin getCustomerData(int customerId);
	
	public Integer logoutCustomer(CustomerLogin logout);

}
