package com.zensar.banking.dao;

import com.zensar.banking.model.CustomerAccount;
import com.zensar.banking.model.CustomerTransaction;

public interface AccountDao {

	public Integer accountOpen(CustomerAccount request);
	
	public CustomerAccount getLatestAccount();
	
	public CustomerTransaction getLatestTransaction();
	
	public Integer depositAmount(CustomerAccount deposit);
	
	public Integer withdrawAmount(CustomerAccount withdraw);
}
