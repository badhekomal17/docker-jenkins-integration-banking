package com.zensar.banking.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zensar.banking.dao.CustomerRegistrationDao;
import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerRegistration;
import com.zensar.banking.model.StatusData;

@Component
public class ValidateField {
	@Autowired
	CustomerRegistrationDao registerDao;

	@Autowired
	StatusData status;

	@Autowired
	BaseResponse baseResponse;

	@Autowired
	Validation validate;

	private static final String NAMES = "names";

	public BaseResponse validateFieldData(CustomerRegistration customer) {

		boolean result = false;
		result = validate.validateData(NAMES, customer.getCustomerFirstName());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid First Name!! First Name is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCustomerLastName());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Last Name!! Last Name is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCurrentCity());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current City!! Current City is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCurrentState());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current State!! Current State is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCurrentCountry());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current Country!! Current Country is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("pinCode", customer.getCurrentPinCode());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current Pin Code!! Current Pin Code is not emplty and allow only numbers!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getPermanentCity());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent City!! Permanent City is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getPermanentState());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent State!! Permanent State is not emplty and allow only characters!!!....");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getPermanentCountry());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent Country!! Permanent Country is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("pinCode", customer.getPermanentPinCode());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent Pin Code!! Permanent Pin Code is not emplty and allow only numbers!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("email", customer.getCustomerEmail());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Email Address!! Email address contains @ symbol !!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("adharNo", customer.getCustomerAadharCardNo());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Aadhar Card!! Aadhar Card No. must be in 12 digits!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("panNo", customer.getCustomerPanNo().toUpperCase());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Pan No!! Pan Card No. must be in first 5 digit in alphabets then 4 digit numbers and then 1 digit aplphabet!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("mobileNo", customer.getCustomerMobileNo());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Mobile No.!! Mobile No. must be in 10 digits!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("password", customer.getPassword());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Customer Password!! Password must have atleast one special symbol, atleast one alphabets in capital letter and atleast one number.");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
		if (result) {
			return new BaseResponse(new StatusData("Ok", 200), null);
		}
		return baseResponse;
	}

	// For update customer
	public BaseResponse updateFieldData(CustomerRegistration customer) {
		boolean result = false;
		result = validate.validateData(NAMES, customer.getCustomerFirstName());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid First Name!! First Name is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCustomerLastName());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Last Name!! Last Name is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCurrentCity());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current City!! Current City is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCurrentState());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current State!! Current State is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getCurrentCountry());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current Country!! Current Country is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("pinCode", customer.getCurrentPinCode());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Current Pin Code!! Current Pin Code is not emplty and allow only numbers!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getPermanentCity());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent City!! Permanent City is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getPermanentState());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent State!! Permanent State is not emplty and allow only characters!!!....");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData(NAMES, customer.getPermanentCountry());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent Country!! Permanent Country is not emplty and allow only characters!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("pinCode", customer.getPermanentPinCode());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage(
					"Invalid Permanent Pin Code!! Permanent Pin Code is not emplty and allow only numbers!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("email", customer.getCustomerEmail());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Email Address!! Email address contains @ symbol !!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		result = validate.validateData("mobileNo", customer.getCustomerMobileNo());
		if (!result) {
			status.setStatusCode(2);
			status.setStatusMessage("Invalid Mobile No.!! Mobile No. must be in 10 digits!!!...");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}

		if (result) {
			return new BaseResponse(new StatusData("Ok", 200), null);
		}
		return baseResponse;
	}
}
