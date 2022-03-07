package com.zensar.banking.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource({ "classpath:accountQueries.properties" })
public class AccountQueryConstant {
	
	private String fetchId;
	private String savingTypeCheck;
	private String inserAccountData;
	private String insertTransactionData;
	private String currentTypeCheck;
	private String latesCustomerAccountDetail;
	private String latesTransactionalDetail;
	private String customerBalance;
	private String updateAccount;
	private String transactionHistory;
	private String checkAccountNo;

	public String getFetchId() {
		return fetchId;
	}

	public void setFetchId(String fetchId) {
		this.fetchId = fetchId;
	}

	public String getSavingTypeCheck() {
		return savingTypeCheck;
	}

	public void setSavingTypeCheck(String savingTypeCheck) {
		this.savingTypeCheck = savingTypeCheck;
	}

	public String getInserAccountData() {
		return inserAccountData;
	}

	public void setInserAccountData(String inserAccountData) {
		this.inserAccountData = inserAccountData;
	}

	public String getInsertTransactionData() {
		return insertTransactionData;
	}

	public void setInsertTransactionData(String insertTransactionData) {
		this.insertTransactionData = insertTransactionData;
	}

	public String getCurrentTypeCheck() {
		return currentTypeCheck;
	}

	public void setCurrentTypeCheck(String currentTypeCheck) {
		this.currentTypeCheck = currentTypeCheck;
	}

	public String getLatesCustomerAccountDetail() {
		return latesCustomerAccountDetail;
	}

	public void setLatesCustomerAccountDetail(String latesCustomerAccountDetail) {
		this.latesCustomerAccountDetail = latesCustomerAccountDetail;
	}

	public String getLatesTransactionalDetail() {
		return latesTransactionalDetail;
	}

	public void setLatesTransactionalDetail(String latesTransactionalDetail) {
		this.latesTransactionalDetail = latesTransactionalDetail;
	}

	public String getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(String customerBalance) {
		this.customerBalance = customerBalance;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public String getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(String transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public String getCheckAccountNo() {
		return checkAccountNo;
	}

	public void setCheckAccountNo(String checkAccountNo) {
		this.checkAccountNo = checkAccountNo;
	}
}
