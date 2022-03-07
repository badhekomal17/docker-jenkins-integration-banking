package com.zensar.banking.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "customer_account")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@SqlResultSetMapping(name="CUSTOMER_ACCOUNT_DATA", classes = {
		@ConstructorResult(targetClass = CustomerAccount.class, columns = {
				@ColumnResult(name = "customerId"),
				@ColumnResult(name = "customerAccountNo"),
				@ColumnResult(name = "customerAccountType"),
				@ColumnResult(name = "customerBalance")
		})
})
public class CustomerAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_account_id", nullable = false)
	int customerAccountId;
	@Column(name = "customer_id", nullable = false)
	int customerId;
	@Column(name = "customer_account_no", nullable = false)
	String customerAccountNo;
	@Column(name = "customer_account_type", nullable = false)
	String customerAccountType;
	@Column(name = "customer_balance", nullable = false)
	int customerBalance;
	int customerTransactionAmount;

	public int getCustomerTransactionAmount() {
		return customerTransactionAmount;
	}

	public void setCustomerTransactionAmount(int customerTransactionAmount) {
		this.customerTransactionAmount = customerTransactionAmount;
	}

	public int getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(int customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAccountNo() {
		return customerAccountNo;
	}

	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = customerAccountNo;
	}

	public String getCustomerAccountType() {
		return customerAccountType;
	}

	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}

	public int getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}

	public CustomerAccount() {
		super();
	}

	public CustomerAccount(int customerAccountId, int customerId, String customerAccountNo, String customerAccountType,
			int customerBalance) {
		super();
		this.customerAccountId = customerAccountId;
		this.customerId = customerId;
		this.customerAccountNo = customerAccountNo;
		this.customerAccountType = customerAccountType;
		this.customerBalance = customerBalance;
	}

	public CustomerAccount(int customerId, String customerAccountNo, String customerAccountType, int customerBalance) {
		super();
		this.customerId = customerId;
		this.customerAccountNo = customerAccountNo;
		this.customerAccountType = customerAccountType;
		this.customerBalance = customerBalance;
	}

	@Override
	public String toString() {
		return "CustomerAccount [customerAccountId=" + customerAccountId + ", customerId=" + customerId
				+ ", customerAccountNo=" + customerAccountNo + ", customerAccountType=" + customerAccountType
				+ ", customerBalance=" + customerBalance + ", customerTransactionAmount=" + customerTransactionAmount
				+ "]";
	}

}
