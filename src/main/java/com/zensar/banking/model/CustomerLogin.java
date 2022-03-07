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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "customer_login")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@SqlResultSetMapping(name = "CUSTOMER_LOGIN_DATA", classes = {
		@ConstructorResult(targetClass = CustomerLogin.class, columns = { @ColumnResult(name = "customerId"),
				@ColumnResult(name = "customerEmail"), @ColumnResult(name = "customerPassword"),
				@ColumnResult(name = "status") }) })

@SqlResultSetMapping(name = "CUSTOMER_DETAILS", classes = {
		@ConstructorResult(targetClass = CustomerLogin.class, columns = { @ColumnResult(name = "customerId"),
				@ColumnResult(name = "customerEmail"), @ColumnResult(name = "customerPassword") }) })
public class CustomerLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_login_id", nullable = false)
	int customerLoginId;
	@Column(name = "customer_id", nullable = false)
	int customerId;
	@Column(name = "customer_email", nullable = false)
	String customerEmail;
	@Column(name = "customer_password", nullable = false)
	String customerPassword;
	@Column(name = "status", nullable = false)
	String status;
	
	@Transient
	String oldPassword;
	
	@Transient
	String newPassword;
	
	public CustomerLogin(int customerLoginId, int customerId, String customerEmail, String customerPassword,
			String status) {
		super();
		this.customerLoginId = customerLoginId;
		this.customerId = customerId;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.status = status;
	}

	public CustomerLogin(int customerId, String customerEmail, String customerPassword, String status) {
		super();
		this.customerId = customerId;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.status = status;
	}

	public CustomerLogin(int customerId, String customerEmail, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
	}

	public CustomerLogin() {
		super();
	}

	public int getCustomerLoginId() {
		return customerLoginId;
	}

	public void setCustomerLoginId(int customerLoginId) {
		this.customerLoginId = customerLoginId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "CustomerLogin [customerLoginId=" + customerLoginId + ", customerId=" + customerId + ", customerEmail="
				+ customerEmail + ", customerPassword=" + customerPassword + ", status=" + status + ", oldPassword="
				+ oldPassword + ", newPassword=" + newPassword + "]";
	}

}