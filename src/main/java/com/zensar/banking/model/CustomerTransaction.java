package com.zensar.banking.model;

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
@Table(name = "customer_transaction")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@SqlResultSetMapping(name="CUSTOMER_TRANSACTION_DATA", classes = {
		@ConstructorResult(targetClass = CustomerTransaction.class, columns = {
				@ColumnResult(name = "customerId"),
				@ColumnResult(name = "customerAccountNo"),
				@ColumnResult(name = "customerTransactionType"),
				@ColumnResult(name = "customerTransactionAmount"),
				@ColumnResult(name = "customerTotalBalance")
		})
})
public class CustomerTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int customerTransactionId;
	int customerId;
	String customerAccountNo;
	String customerTransactionType;
	int customerTransactionAmount;
	int customerTotalBalance;
	String TransactionDate;

	public CustomerTransaction() {
		super();
	}

	public CustomerTransaction(int customerTransactionId, int customerId, String customerAccountNo,
			String customerTransactionType, int customerTransactionAmount, int customerTotalBalance,
			String transactionDate) {
		super();
		this.customerTransactionId = customerTransactionId;
		this.customerId = customerId;
		this.customerAccountNo = customerAccountNo;
		this.customerTransactionType = customerTransactionType;
		this.customerTransactionAmount = customerTransactionAmount;
		this.customerTotalBalance = customerTotalBalance;
		this.TransactionDate = transactionDate;
	}

	public int getCustomerTransactionId() {
		return customerTransactionId;
	}

	public void setCustomerTransactionId(int customerTransactionId) {
		this.customerTransactionId = customerTransactionId;
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

	public String getCustomerTransactionType() {
		return customerTransactionType;
	}

	public void setCustomerTransactionType(String customerTransactionType) {
		this.customerTransactionType = customerTransactionType;
	}

	public int getCustomerTransactionAmount() {
		return customerTransactionAmount;
	}

	public void setCustomerTransactionAmount(int customerTransactionAmount) {
		this.customerTransactionAmount = customerTransactionAmount;
	}

	public int getCustomerTotalBalance() {
		return customerTotalBalance;
	}

	public void setCustomerTotalBalance(int customerTotalBalance) {
		this.customerTotalBalance = customerTotalBalance;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	public CustomerTransaction(int customerId, String customerAccountNo, String customerTransactionType,
			int customerTransactionAmount, int customerTotalBalance) {
		super();
		this.customerId = customerId;
		this.customerAccountNo = customerAccountNo;
		this.customerTransactionType = customerTransactionType;
		this.customerTransactionAmount = customerTransactionAmount;
		this.customerTotalBalance = customerTotalBalance;
	}

	@Override
	public String toString() {
		return "CustomerTransaction [customerTransactionId=" + customerTransactionId + ", customerId=" + customerId
				+ ", customerAccountNo=" + customerAccountNo + ", customerTransactionType="
				+ customerTransactionType + ", customerTransactionAmount=" + customerTransactionAmount
				+ ", customerTotalBalance=" + customerTotalBalance + ", TransactionDate=" + TransactionDate + "]";
	}

}
