package com.zensar.banking.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource({ "classpath:registerQueries.properties" })
public class QueryConstant {
	
	private String registerCustomer;
	private String getCustomerList;
	private String registerLogin;
	private String checkPanAadhar;
	private String fetchCustomerIdUsingPan;
	private String checkStatus;
	private String updateRegisterCustomer;
	private String latestRegisterCustomerDetail;
	private String fetchCustomerByIdData;
	private String deleteCustomer;
	private String deleteCustomerLoginById;
	private String deleteCustomerTransactionById;
	private String deleteCustomerAccountById;
	private String deleteCustomerRegisterationById;
	private String fetchCustomerOldPassword;
	
	// For Login
	private String updatePasswordLogin;
	private String updatePasswordRegistration;
	private String customerLogin;
	private String CustomerLogout;
	private String loginDataById;

	// For register new customer
	public String getRegisterCustomer() {
		return registerCustomer;
	}

	public void setRegisterCustomer(String registerCustomer) {
		this.registerCustomer = registerCustomer;
	}
	
	// To fetch all customer details
	public String getGetCustomerList() {
		return getCustomerList;
	}

	public void setGetCustomerList(String getCustomerList) {
		this.getCustomerList = getCustomerList;
	}

	// To register new Customer details stored in login table	
	public String getRegisterLogin() {
		return registerLogin;
	}

	public void setRegisterLogin(String registerLogin) {
		this.registerLogin = registerLogin;
	}

	// To check Pan & Aadhar no exist or not in database
	public String getCheckPanAadhar() {
		return checkPanAadhar;
	}

	public void setCheckPanAadhar(String checkPanAadhar) {
		this.checkPanAadhar = checkPanAadhar;
	}

	// To fetch customerId from Pan no
	public String getFetchCustomerIdUsingPan() {
		return fetchCustomerIdUsingPan;
	}

	public void setFetchCustomerIdUsingPan(String fetchCustomerIdUsingPan) {
		this.fetchCustomerIdUsingPan = fetchCustomerIdUsingPan;
	}

	// To check status from customerId
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	// To update Customer details
	public String getUpdateRegisterCustomer() {
		return updateRegisterCustomer;
	}

	public void setUpdateRegisterCustomer(String updateRegisterCustomer) {
		this.updateRegisterCustomer = updateRegisterCustomer;
	}
	
	//To fetch newly registered customer details for registerCustomerResponse
	public String getLatestRegisterCustomerDetail() {
		return latestRegisterCustomerDetail;
	}

	public void setLatestRegisterCustomerDetail(String latestRegisterCustomerDetail) {
		this.latestRegisterCustomerDetail = latestRegisterCustomerDetail;
	}

	// To Fetch Customer Details by Id using RequsetBody		
	public String getFetchCustomerByIdData() {
		return fetchCustomerByIdData;
	}

	public void setFetchCustomerByIdData(String fetchCustomerByIdData) {
		this.fetchCustomerByIdData = fetchCustomerByIdData;
	}
	
	// To delete customer details by id from login table
	public String getDeleteCustomerLoginById() {
		return deleteCustomerLoginById;
	}

	public void setDeleteCustomerLoginById(String deleteCustomerLoginById) {
		this.deleteCustomerLoginById = deleteCustomerLoginById;
	}

	// To delete customer details by id from transaction table
	public String getDeleteCustomerTransactionById() {
		return deleteCustomerTransactionById;
	}

	public void setDeleteCustomerTransactionById(String deleteCustomerTransactionById) {
		this.deleteCustomerTransactionById = deleteCustomerTransactionById;
	}

	// To delete customer details by id from account table
	public String getDeleteCustomerAccountById() {
		return deleteCustomerAccountById;
	}

	public void setDeleteCustomerAccountById(String deleteCustomerAccountById) {
		this.deleteCustomerAccountById = deleteCustomerAccountById;
	}

	// To delete customer details by id from registration table
	public String getDeleteCustomerRegisterationById() {
		return deleteCustomerRegisterationById;
	}

	public void setDeleteCustomerRegisterationById(String deleteCustomerRegisterationById) {
		this.deleteCustomerRegisterationById = deleteCustomerRegisterationById;
	}

	// To change password in login
	public String getUpdatePasswordLogin() {
		return updatePasswordLogin;
	}

	public void setUpdatePasswordLogin(String updatePasswordLogin) {
		this.updatePasswordLogin = updatePasswordLogin;
	}

	// To change password in registration
	public String getUpdatePasswordRegistration() {
		return updatePasswordRegistration;
	}

	public void setUpdatePasswordRegistration(String updatePasswordRegistration) {
		this.updatePasswordRegistration = updatePasswordRegistration;
	}

	// To Login Customer
	public String getCustomerLogin() {
		return customerLogin;
	}

	public void setCustomerLogin(String customerLogin) {
		this.customerLogin = customerLogin;
	}

	// To Logout Customer
	public String getCustomerLogout() {
		return CustomerLogout;
	}

	public void setCustomerLogout(String customerLogout) {
		CustomerLogout = customerLogout;
	}

	//To Fetch Customer Data from login table by id
	public String getLoginDataById() {
		return loginDataById;
	}

	public void setLoginDataById(String loginDataById) {
		this.loginDataById = loginDataById;
	}

	// To fetch customer old password by id
	public String getFetchCustomerOldPassword() {
		return fetchCustomerOldPassword;
	}

	public void setFetchCustomerOldPassword(String fetchCustomerOldPassword) {
		this.fetchCustomerOldPassword = fetchCustomerOldPassword;
	}

	// Delete customer (Change status = Inactive )
	public String getDeleteCustomer() {
		return deleteCustomer;
	}

	public void setDeleteCustomer(String deleteCustomer) {
		this.deleteCustomer = deleteCustomer;
	}	
	
}
