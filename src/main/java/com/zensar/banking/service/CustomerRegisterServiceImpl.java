package com.zensar.banking.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.banking.constant.ValidateField;
import com.zensar.banking.dao.CustomerRegistrationDao;
import com.zensar.banking.model.BaseResponse;
import com.zensar.banking.model.CustomerRegistration;
import com.zensar.banking.model.StatusData;

@Service
@Transactional
public class CustomerRegisterServiceImpl implements CustomerRegisterService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRegisterServiceImpl.class);

	@Autowired
	CustomerRegistrationDao registerDao;

	@Autowired
	StatusData status;

	@Autowired
	BaseResponse baseResponse;

	@Autowired
	ValidateField field;

	// To Register Customer
	@Override
	public BaseResponse registerCustomer(CustomerRegistration customer) {
		logger.info("Start CustomerRegisterServiceImpl class -> registerCustomer() ");

		BaseResponse bs = field.validateFieldData(customer);

		if (bs.getStatus().getStatusCode() == 200 && (bs.getStatus().getStatusMessage().equalsIgnoreCase("Ok"))) {
			// customerRegistration
			Integer customerStatus = registerDao.registerCustomer(customer);
			if (customerStatus == 1) {
				// Here created new getLatestCustomer() for to fetch response
				CustomerRegistration customerData = registerDao.getLatestCustomer();
				status.setStatusCode(1);
				status.setStatusMessage("Register request processed successfully!!");
				baseResponse.setData(customerData);
				baseResponse.setStatus(status);
				return baseResponse;
			} else {
				status.setStatusCode(-1);
				status.setStatusMessage("Unable to register customer!! Duplicate Entry for Aadhar Card or Pan Card!!");
				baseResponse.setData(null);
				baseResponse.setStatus(status);
				return baseResponse;
			}
		} else {
			return bs;
		}
	}

	// To Get All CustomerList
	@Override
	public BaseResponse getCustomerList(Integer start, Integer offset) {
		List<CustomerRegistration> customers = registerDao.getCustomerList(start, offset);
		if (!customers.isEmpty()) {
			status.setStatusCode(1);
			status.setStatusMessage("Get All Customer List Fetch successfully!!");
			baseResponse.setData(customers);
			baseResponse.setStatus(status);
			return baseResponse;
		} else {
			status.setStatusCode(-1);
			status.setStatusMessage("Unable to fetch Customer List!!");
			baseResponse.setData(customers);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}

	// To get CustomerDetails By customerId using RequsetBody
	@Override
	public BaseResponse getCustomerByIdData(String id) {
		CustomerRegistration customer = null;
		for (int i = 0; i < id.length(); i++) {
			if ((Character.isDigit(id.charAt(i))) == false)
				return new BaseResponse(new StatusData("Invalid Customer Id\n Only Digits allowed", -1), null);
		}

		int customerId = Integer.parseInt(id);
		
		if (customerId != 0) {
			// get Data by Id
			customer = registerDao.getCustomerByIdData(customerId);
			if (customer != null) {
				status.setStatusCode(1);
				status.setStatusMessage("Get Data processed successfully!!");
				baseResponse.setData(customer);
				baseResponse.setStatus(status);
				return baseResponse;
			} else {
				status.setStatusCode(-1);
				status.setStatusMessage("Unable to Get customer!!");
				baseResponse.setData(null);
				baseResponse.setStatus(status);
				return baseResponse;
			}
		} else {
			status.setStatusCode(-2);
			status.setStatusMessage("No such customer id found!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}

	// To Update Customer
	@Override
	public BaseResponse updateRegisterCustomer(CustomerRegistration customer) {
		BaseResponse bs = field.updateFieldData(customer);

		if (bs.getStatus().getStatusCode() == 200 && (bs.getStatus().getStatusMessage().equalsIgnoreCase("Ok"))) {
			// Update Registered Customer
			Integer customerStatus = registerDao.updateRegisterCustomer(customer);
			if (customerStatus == 1) {
				CustomerRegistration customerData = registerDao.getCustomerByIdData(customer.getCustomerId());
				status.setStatusCode(1);
				status.setStatusMessage("Update request processed successfully!!");
				baseResponse.setData(customerData);
				baseResponse.setStatus(status);
				return baseResponse;
			} else {
				status.setStatusCode(-1);
				status.setStatusMessage(
						"Unable to update customer!!Status is in Inactive State Or CustomerId wrong !!");
				baseResponse.setData(null);
				baseResponse.setStatus(status);
				return baseResponse;
			}
		} else {
			return bs;
		}
	}

	// To delete Customer

	@Override
	public BaseResponse deleteRegisterCustomer(int customerId) {
		if (customerId != 0) {
			Integer deleteStatus = registerDao.deleteCustomer(customerId);
			if (deleteStatus == 1) {
				status.setStatusCode(1);
				status.setStatusMessage("Customer Deleted successfully!!");
				baseResponse.setData(deleteStatus);
				baseResponse.setStatus(status);
				return baseResponse;
			} else {
				status.setStatusCode(-1);
				status.setStatusMessage("Unable to delete Customer!!");
				baseResponse.setData(null);
				baseResponse.setStatus(status);
				return baseResponse;
			}
		} else {
			status.setStatusCode(-2);
			status.setStatusMessage("No such customer id found!!");
			baseResponse.setData(null);
			baseResponse.setStatus(status);
			return baseResponse;
		}
	}
}