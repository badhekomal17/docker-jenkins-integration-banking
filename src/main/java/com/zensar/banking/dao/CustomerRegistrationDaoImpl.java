package com.zensar.banking.dao;

import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zensar.banking.constant.AccountQueryConstant;
import com.zensar.banking.constant.QueryConstant;
import com.zensar.banking.model.CustomerRegistration;

@Repository
public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationDaoImpl.class);

	@Autowired
	QueryConstant queryConstant;

	@Autowired
	AccountQueryConstant accountQueryConstant;

	@PersistenceContext
	EntityManager entityManager;
	String message = "";

	public static final String START = "start";
	public static final String OFFSET = "offset";
	public static final String GET_CUSTOMER_LIST = "GET_CUSTOMER_LIST";

	@Transactional
	@Override
	public Integer registerCustomer(CustomerRegistration customer) {
		logger.info("Start CustomerRegistrationDaoImpl class -> registerCustomer()");
		Integer status = 0;
		String customerFirstName = customer.getCustomerFirstName();
		String customerLastName = customer.getCustomerLastName();
		String customerEmail = customer.getCustomerEmail();
		String customerMobileNo = customer.getCustomerMobileNo();
		String customerAadharCardNo = customer.getCustomerAadharCardNo();
		String customerPanNo = customer.getCustomerPanNo();
		String currentCity = customer.getCurrentCity();
		String currentState = customer.getCurrentState();
		String currentCountry = customer.getCurrentCountry();
		String currentPinCode = customer.getCurrentPinCode();
		String permanentCity = customer.getPermanentCity();
		String permanentState = customer.getPermanentState();
		String permanentCountry = customer.getPermanentCountry();
		String permanentPinCode = customer.getPermanentPinCode();

		Base64.Encoder encoder = Base64.getUrlEncoder();
		// To Encode Password
		String customerPassword = encoder.encodeToString((customer.getPassword()).getBytes());

		// To check Customer AAdhar No & Pan No already exist or not in table
		Query queryData = entityManager.createNativeQuery(queryConstant.getCheckPanAadhar())
				.setParameter("customerAadharCardNo", customerAadharCardNo)
				.setParameter("customerPanNo", customerPanNo);
		List resultList = queryData.getResultList();

		if (resultList.isEmpty()) {
			Query queryRegistration = entityManager.createNativeQuery(queryConstant.getRegisterCustomer())
					.setParameter("customerFirstName", customerFirstName)
					.setParameter("customerLastName", customerLastName).setParameter("customerEmail", customerEmail)
					.setParameter("customerMobileNo", customerMobileNo)
					.setParameter("customerAadharCardNo", customerAadharCardNo)
					.setParameter("customerPanNo", customerPanNo).setParameter("currentCity", currentCity)
					.setParameter("currentState", currentState).setParameter("currentCountry", currentCountry)
					.setParameter("currentPinCode", currentPinCode).setParameter("permanentCity", permanentCity)
					.setParameter("permanentState", permanentState).setParameter("permanentCountry", permanentCountry)
					.setParameter("permanentPinCode", permanentPinCode)
					.setParameter("customerPassword", customerPassword);
			try {
				status = queryRegistration.executeUpdate();

				// To fetch customer Id for login table
				Integer cid = (Integer) entityManager.createNativeQuery(queryConstant.getFetchCustomerIdUsingPan())
						.setParameter("customerPanNo", customerPanNo).getSingleResult();
				String customerStatus = "Active";

				// Record inserted in login table
				Query queryLogin = entityManager.createNativeQuery(queryConstant.getRegisterLogin())
						.setParameter("cid", cid).setParameter("customerEmail", customerEmail)
						.setParameter("customerPassword", customerPassword)
						.setParameter("customerStatus", customerStatus);
				status = queryLogin.executeUpdate();
				return status;

			} catch (Exception e) {
				logger.error("Error while registering new customer: ", e);
			}
		} else {
			return status;
		}
		logger.info("End CustomerRegistrationDaoImpl class -> registerCustomer()");
		return status;
	}

	// To Get Customer List
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerRegistration> getCustomerList(Integer start, Integer offset) {
		List<CustomerRegistration> customerList = null;
		try {
			Query queryGetCustomerList = entityManager
					.createNativeQuery(queryConstant.getGetCustomerList(), "CUSTOMER_DATA").setParameter(START, start)
					.setParameter(OFFSET, offset);

			customerList = queryGetCustomerList.getResultList();
			return customerList;
		} catch (Exception e) {
			logger.error("Error while fetching all customer list : ", e);
		}
		return customerList;
	}

	// To Fetch Customer Details by Id (RequsetBody)
	@Transactional
	@Override
	public CustomerRegistration getCustomerByIdData(int customerId) {
		CustomerRegistration customerData = new CustomerRegistration();
		try {
			// To check inserted CustomerId is available in database or not
			Query dataById = entityManager.createNativeQuery(accountQueryConstant.getFetchId())
					.setParameter("customerId", customerId);
			int cid = (int) dataById.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("No Result found: ", e);
			return null;
		}

		try {
			Query queryData = entityManager
					.createNativeQuery(queryConstant.getFetchCustomerByIdData(), GET_CUSTOMER_LIST)
					.setParameter("customerId", customerId);
			customerData = (CustomerRegistration) queryData.getSingleResult();
			return customerData;
		} catch (NoResultException e) {
			logger.debug("No Result found: ", e);
		}
		return customerData;
	}

	// To Update Customer Details
	@Transactional
	@Override
	public Integer updateRegisterCustomer(CustomerRegistration customer) {
		logger.info("Start CustomerRegistrationDaoImpl class -> updateRegisterCustomer()");
		Integer status = 0;
		int customerId = customer.getCustomerId();
		String customerFirstName = customer.getCustomerFirstName();
		String customerLastName = customer.getCustomerLastName();
		String customerEmail = customer.getCustomerEmail();
		String customerMobileNo = customer.getCustomerMobileNo();
		String currentCity = customer.getCurrentCity();
		String currentState = customer.getCurrentState();
		String currentCountry = customer.getCurrentCountry();
		String currentPinCode = customer.getCurrentPinCode();
		String permanentCity = customer.getPermanentCity();
		String permanentState = customer.getPermanentState();
		String permanentCountry = customer.getPermanentCountry();
		String permanentPinCode = customer.getPermanentPinCode();
		try {
			// To check inserted CustomerId is available in database or not
			int cid = 0;
			try {
				cid = (Integer) entityManager.createNativeQuery(accountQueryConstant.getFetchId())
						.setParameter("customerId", customerId).getSingleResult();
			} catch (NoResultException e) {
				cid = 0;
			}
			if (cid == 0) {
				return -1;
			}

			// To check Customer status is Active or Inactive
			Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
					.setParameter("customerId", customerId);
			String checkStatus = (String) statusquery.getSingleResult();

			if ((cid == customerId) && (checkStatus.equalsIgnoreCase("Active"))) {
				// To update Registered Customer
				Query updateQuery = entityManager
						.createNativeQuery(queryConstant.getUpdateRegisterCustomer(), GET_CUSTOMER_LIST)
						.setParameter("customerId", customerId).setParameter("customerFirstName", customerFirstName)
						.setParameter("customerLastName", customerLastName).setParameter("customerEmail", customerEmail)
						.setParameter("customerMobileNo", customerMobileNo)
						.setParameter("currentCity", currentCity)
						.setParameter("currentState", currentState).setParameter("currentCountry", currentCountry)
						.setParameter("currentPinCode", currentPinCode).setParameter("permanentCity", permanentCity)
						.setParameter("permanentState", permanentState)
						.setParameter("permanentCountry", permanentCountry)
						.setParameter("permanentPinCode", permanentPinCode);

				status = updateQuery.executeUpdate();
				return status;
			} else {
				return -1;
			} // end else
		} catch (Exception e) {
			logger.error("Error while updating new customer : ", e);
		}

		logger.info("End CustomerRegistrationDaoImpl class -> updateRegisterCustomer()");
		return status;
	}

	// To fetch newly registered customer details for registerCustomerResponse
	@Transactional
	@Override
	public CustomerRegistration getLatestCustomer() {
		CustomerRegistration customerData = new CustomerRegistration();
		try {
			Query queryData = entityManager.createNativeQuery(queryConstant.getLatestRegisterCustomerDetail(),
					GET_CUSTOMER_LIST);
			customerData = (CustomerRegistration) queryData.getSingleResult();
			return customerData;
		} catch (Exception e) {
			logger.error("Error While fetching latest registered customer Data : ", e);
		}
		return customerData;
	}

	// To Delete Customer from child table & parent table
	@Override
	public Integer deleteCustomer(int customerId) {
		try {
			// To check inserted CustomerId is available in database or not
			Query dataById = entityManager.createNativeQuery(accountQueryConstant.getFetchId())
					.setParameter("customerId", customerId);
			int cid = (int) dataById.getSingleResult();
		} catch (NoResultException e) {
			return -2;
		}
		try {
//			Query deleteQuery = entityManager.createNativeQuery(queryConstant.getDeleteCustomer())
//					.setParameter("customerId", customerId);
//			Integer status = deleteQuery.executeUpdate();
			
			Query loginQuery = entityManager.createNativeQuery(queryConstant.getDeleteCustomerLoginById())
					.setParameter("customerId", customerId);
			int executeUpdate = loginQuery.executeUpdate();
			Query transactionQuery = entityManager.createNativeQuery(queryConstant.getDeleteCustomerTransactionById())
					.setParameter("customerId", customerId);
			int executeUpdate1 = transactionQuery.executeUpdate();
			Query accountQuery = entityManager.createNativeQuery(queryConstant.getDeleteCustomerAccountById())
					.setParameter("customerId", customerId);
			int executeUpdate2 = accountQuery.executeUpdate();
			Query registrationQuery = entityManager
					.createNativeQuery(queryConstant.getDeleteCustomerRegisterationById())
					.setParameter("customerId", customerId);
			int executeUpdate3 = registrationQuery.executeUpdate();
			if (executeUpdate == 1 || executeUpdate1 == 1 || executeUpdate2 == 1 || executeUpdate3 == 1) {
				return 1;
			} else
				return -1;

		} catch (Exception e) {
			logger.error("Error while deleteing record :", e);
		}
		return 1;
	}
}