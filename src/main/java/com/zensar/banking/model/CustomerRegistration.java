package com.zensar.banking.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "customer_registration")
@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name="CUSTOMER_DATA", classes = {
				@ConstructorResult(targetClass = CustomerRegistration.class, columns = {
					@ColumnResult(name = "customerId"),
					@ColumnResult(name = "customerFirstName"),
					@ColumnResult(name = "customerLastName")
				})
		}),

@SqlResultSetMapping(name="GET_CUSTOMER_LIST", classes = {
		@ConstructorResult(targetClass = CustomerRegistration.class, columns = {
				@ColumnResult(name = "customerId"),
				@ColumnResult(name = "customerFirstName"),
				@ColumnResult(name = "customerLastName"),
				@ColumnResult(name = "customerEmail"),
				@ColumnResult(name = "customerMobileNo"),
				@ColumnResult(name = "customerAadharCardNo"),
				@ColumnResult(name = "customerPanNo"),
				@ColumnResult(name = "currentCity"),
				@ColumnResult(name = "currentState"),
				@ColumnResult(name = "currentCountry"),
				@ColumnResult(name = "currentPinCode"),
				@ColumnResult(name = "permanentCity"),
				@ColumnResult(name = "permanentState"),
				@ColumnResult(name = "permanentCountry"),
				@ColumnResult(name = "permanentPinCode")})
	})
})

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id", nullable = false)
	@NotNull
	int customerId;
	@Column(name = "customer_first_name", nullable = false)
	String customerFirstName;
	@Column(name = "customer_last_name", nullable = false)
	String customerLastName;
	@Column(name = "customer_email", nullable = false)
	String customerEmail;
	@Column(name = "customer_mobile_no", nullable = false)
	String customerMobileNo;
	@Column(unique = true,nullable = false, name = "customer_aadhar_card_no")
	String customerAadharCardNo;
	@Column(unique = true, nullable = false, name = "customer_pan_no")
	String customerPanNo;
	@Column(name = "current_city", nullable = false)
	String currentCity;
	@Column(name = "current_state", nullable = false)
	String currentState;
	@Column(name = "current_country", nullable = false)
	String currentCountry;
	@Column(name = "current_pin_code", nullable = false)
	String currentPinCode;
	@Column(name = "permanent_city", nullable = false)
	String permanentCity;
	@Column(name = "permanent_state", nullable = false)
	String permanentState;
	@Column(name = "permanent_country", nullable = false)
	String permanentCountry;
	@Column(name = "permanent_pin_code", nullable = false)
	String permanentPinCode;
	@Column(name = "customer_password", nullable = false)
	String password;
	
	@Transient
	String id;
	
	
	//Without Password Field
	public CustomerRegistration(@NotNull int customerId, String customerFirstName, String customerLastName) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
	}	
	
	//With Password Field
	public CustomerRegistration(int customerId, String customerFirstName, String customerLastName, String customerEmail,
			String customerMobileNo, String customerAadharCardNo, String customerPanNo, String currentCity,
			String currentState, String currentCountry, String currentPinCode, String permanentCity,
			String permanentState, String permanentCountry, String permanentPinCode) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmail = customerEmail;
		this.customerMobileNo = customerMobileNo;
		this.customerAadharCardNo = customerAadharCardNo;
		this.customerPanNo = customerPanNo;
		this.currentCity = currentCity;
		this.currentState = currentState;
		this.currentCountry = currentCountry;
		this.currentPinCode = currentPinCode;
		this.permanentCity = permanentCity;
		this.permanentState = permanentState;
		this.permanentCountry = permanentCountry;
		this.permanentPinCode = permanentPinCode;
	}

	public CustomerRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerName) {
		this.customerFirstName = customerName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getCustomerAadharCardNo() {
		return customerAadharCardNo;
	}

	public void setCustomerAadharCardNo(String customerAadharCardNo) {
		this.customerAadharCardNo = customerAadharCardNo;
	}

	public String getCustomerPanNo() {
		return customerPanNo;
	}

	public void setCustomerPanNo(String customerPanNo) {
		this.customerPanNo = customerPanNo;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	public String getCurrentPinCode() {
		return currentPinCode;
	}

	public void setCurrentPinCode(String currentPinCode) {
		this.currentPinCode = currentPinCode;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public String getPermanentPinCode() {
		return permanentPinCode;
	}

	public void setPermanentPinCode(String permanentPinCode) {
		this.permanentPinCode = permanentPinCode;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
