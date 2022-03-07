package com.zensar.banking.dao;

import java.util.Base64;

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
import com.zensar.banking.model.CustomerLogin;

@Repository
@Transactional
public class CustomerLoginDaoImpl implements CustomerLoginDao {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationDaoImpl.class);

	@Autowired
	QueryConstant queryConstant;

	@Autowired
	AccountQueryConstant accountQueryConstant;

	@PersistenceContext
	EntityManager entityManager;

	// To change Password
	@Override
	public Integer changePassword(CustomerLogin request) {

		logger.info("Start CustomerLoginDaoImpl class -> changePassword()");
		int result = 0;
		Integer status = 0;

		int customerId = request.getCustomerId();
		String oldPassword = request.getOldPassword();
		String newPassword = request.getNewPassword();

		// To Encode Password new password
		Base64.Encoder encoder = Base64.getUrlEncoder();
		newPassword = encoder.encodeToString(newPassword.getBytes());

		try {
			// To check inserted CustomerId is same or not
			int cid = 0;
			try {
				cid = (Integer) entityManager.createNativeQuery(accountQueryConstant.getFetchId())
						.setParameter("customerId", customerId).getSingleResult();
			} catch (NoResultException e) {
				return -2;
			}
			if (cid == 0) {
				return -2;
			}

			// To fetch old_password from customer_id
			String customerOldPassword = (String) entityManager
					.createNativeQuery(queryConstant.getFetchCustomerOldPassword())
					.setParameter("customerId", customerId).getSingleResult();

			Base64.Decoder decoder = Base64.getUrlDecoder();
			customerOldPassword = new String(decoder.decode(customerOldPassword));

			// To check Customer status is Active or Inactive
			Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
					.setParameter("customerId", customerId);
			String str = (String) statusquery.getSingleResult();

			if (str.equalsIgnoreCase("Active") && (cid == customerId) && (oldPassword.contains(customerOldPassword))) {
				// Update Password in customer_login Table
				Query loginUpdateQuery = entityManager.createNativeQuery(queryConstant.getUpdatePasswordLogin())
						.setParameter("customerId", customerId).setParameter("newPassword", newPassword);
				status = loginUpdateQuery.executeUpdate();

				// Update Password in customer_registration Table
				Query registerUpdateQuery = entityManager
						.createNativeQuery(queryConstant.getUpdatePasswordRegistration())
						.setParameter("customerId", customerId).setParameter("newPassword", newPassword);
				status = registerUpdateQuery.executeUpdate();
			} // end if
			else {
				return -1;
			} // end else
		} // end try
		catch (Exception e) {
			logger.error("Error while updating password:", e);
		}
		logger.info("End CustomerLoginDaoImpl class -> changePassword()");
		return status;
	}

	// For Login Customer
	@Override
	public Integer loginCustomer(CustomerLogin login, String custPassword) {
		logger.info("Start CustomerLoginDaoImpl class -> loginCustomer()");
		Integer status = -1;
		int customerId = login.getCustomerId();
		String customerEmail = login.getCustomerEmail();
		String customerPassword = login.getCustomerPassword();

		CustomerLogin customerData = new CustomerLogin();
		try {
			// To check customer email & password using customer Id
			Query queryData = entityManager.createNativeQuery(queryConstant.getLoginDataById(), "CUSTOMER_DETAILS")
					.setParameter("customerId", customerId);
			customerData = (CustomerLogin) queryData.getSingleResult();

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String decodeString = new String(decoder.decode(customerData.getCustomerPassword()));

			// To check Customer status is Active or Inactive
			Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
					.setParameter("customerId", customerId);
			String str = (String) statusquery.getSingleResult();

			if ((str.equalsIgnoreCase("Inactive")) && (decodeString.equalsIgnoreCase(custPassword))
					&& (customerData.getCustomerId() == customerId)
					&& (customerData.getCustomerEmail().equalsIgnoreCase(customerEmail))) {
				Query loginQuery = entityManager
						.createNativeQuery(queryConstant.getCustomerLogin(), "CUSTOMER_LOGIN_DATA")
						.setParameter("customerId", customerId).setParameter("customerEmail", customerEmail)
						.setParameter("customerPassword", customerPassword);
				status = loginQuery.executeUpdate();
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			logger.error("Error while Customer Login:", e);
		}
		logger.info("End CustomerLoginDaoImpl class -> loginCustomer()");
		return status;
	}

	// For Logout Customer
	@Override
	public Integer logoutCustomer(CustomerLogin logout) {
		logger.info("Start CustomerLoginDaoImpl class -> logoutCustomer()");
		Integer status = -1;
		int customerId = logout.getCustomerId();
		String customerEmail = logout.getCustomerEmail();
		String customerPassword = logout.getCustomerPassword();
		
		CustomerLogin customerData = new CustomerLogin();
		try {
			// To check customer email & password using customer Id
			Query queryData = entityManager.createNativeQuery(queryConstant.getLoginDataById(), "CUSTOMER_DETAILS")
					.setParameter("customerId", customerId);
			customerData = (CustomerLogin) queryData.getSingleResult();

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String decodeString = new String(decoder.decode(customerData.getCustomerPassword()));

			// To check Customer status is Active or Inactive
			Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
					.setParameter("customerId", customerId);
			String str = (String) statusquery.getSingleResult();

			if ((str.equalsIgnoreCase("Active")) && (decodeString.equalsIgnoreCase(customerPassword))
					&& (customerData.getCustomerId() == customerId)
					&& (customerData.getCustomerEmail().equalsIgnoreCase(customerEmail))) {
				Query logoutQuery = entityManager
						.createNativeQuery(queryConstant.getCustomerLogout(), "CUSTOMER_LOGIN_DATA")
						.setParameter("customerId", customerId).setParameter("customerEmail", customerEmail)
						.setParameter("customerPassword", customerPassword);
				status = logoutQuery.executeUpdate();
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			logger.error("Error while Customer Logout:", e);
		}
		logger.info("End CustomerLoginDaoImpl class -> logoutCustomer()");
		return status;
	}

	// To Fetch Customer Data from login table by id For loginCustomer() &
	// changePassword()
	@Override
	public CustomerLogin getCustomerData(int customerId) {
		CustomerLogin customerData = new CustomerLogin();
		try {
			Query queryData = entityManager.createNativeQuery(queryConstant.getLoginDataById(), "CUSTOMER_DETAILS")
					.setParameter("customerId", customerId);
			customerData = (CustomerLogin) queryData.getSingleResult();

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String decodeString = new String(decoder.decode(customerData.getCustomerPassword()));
			customerData.setCustomerPassword(decodeString);
			return customerData;

		} catch (Exception e) {
			logger.error("Error while Fetch Customer Data For loginCustomer() & changePassword() :", e);
		}
		logger.info("End CustomerLoginDaoImpl class -> logoutCustomer()");
		return customerData;
	}
}// End DAO Class
