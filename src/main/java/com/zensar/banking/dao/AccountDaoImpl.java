package com.zensar.banking.dao;

import java.util.Iterator;

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
import com.zensar.banking.model.CustomerAccount;
import com.zensar.banking.model.CustomerTransaction;

@Repository
public class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationDaoImpl.class);

	String message = "";
	Integer result = 0;
	Integer status = 0;

	@Autowired
	AccountQueryConstant accountQueryConstant;

	@Autowired
	QueryConstant queryConstant;

	@PersistenceContext
	EntityManager entityManager;

	// To Open Customer Account
	@Transactional
	@Override
	public Integer accountOpen(CustomerAccount request) {
		logger.info("Start AccountDaoImpl class -> accountOpen()");
		int customerId = request.getCustomerId();
		String customerAccountNo = null;
		String customerAccountType = request.getCustomerAccountType();
		int customerBalance = request.getCustomerBalance();
		String customerTransactionType = "Deposit";
		// For Customer Saving Account
		if (customerAccountType.equalsIgnoreCase("saving")) {
			try {
				// To check inserted CustomerId is same or not
				int cid = 0;
				try {
					cid = (Integer) entityManager.createNativeQuery(accountQueryConstant.getFetchId())
							.setParameter("customerId", customerId).getSingleResult();
				} catch (NoResultException e) {
					cid = 0;
				}
				if (cid == 0) {
					return -2;
				}

				// To generate Account No
				int number = cid + 1111;
				customerAccountNo = "0043201" + number;

				// To check customer saving account already exist or not
				//System.out.println("Hello");
				Query CheckTypequery = entityManager.createNativeQuery(accountQueryConstant.getSavingTypeCheck())
						.setParameter("customerId", customerId)
						.setParameter("customerAccountType", customerAccountType);
				int id = 0;
				try {
					id = (Integer) CheckTypequery.getSingleResult();
				} catch (NoResultException e) {
					id = 0;
				}

				// To check Customer status is Active or Inactive
				Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
						.setParameter("customerId", customerId);
				String checkStatus = (String) statusquery.getSingleResult();

				if ((id == 0) && (cid == customerId) && (checkStatus.equalsIgnoreCase("Active"))) {
					if (customerBalance >= 5000) {
						// Insert Data into customer_Account
						Query insertAccountAuery = entityManager
								.createNativeQuery(accountQueryConstant.getInserAccountData())
								.setParameter("customerId", customerId)
								.setParameter("customerAccountNo", customerAccountNo)
								.setParameter("customerAccountType", customerAccountType)
								.setParameter("customerBalance", customerBalance);
						status = insertAccountAuery.executeUpdate();

						// Insert Data into customer_Transactional
						Query insertTransactionAuery = entityManager
								.createNativeQuery(accountQueryConstant.getInsertTransactionData())
								.setParameter("customerId", customerId)
								.setParameter("customerAccountNo", customerAccountNo)
								.setParameter("customerTransactionType", customerTransactionType)
								.setParameter("customerBalance", customerBalance);
						status = insertTransactionAuery.executeUpdate();
						return status;
					} else {
						return -1;
					}
				} // end if
				else {
					return -2;
				} // else end
			} // end try
			catch (Exception e) {
				logger.error("Error while creating customer account: ", e);
			}
			logger.info("End AccountDaoImpl class -> accountOpen()");
			return status;
		} // if end

		// For Customer Current Account
		else if (customerAccountType.equalsIgnoreCase("current")) {
			try {
				// To check inserted CustomerId is same or not
				int cid = 0;
				try {
					cid = (Integer) entityManager.createNativeQuery(accountQueryConstant.getFetchId())
							.setParameter("customerId", customerId).getSingleResult();
				} catch (NoResultException e) {
					cid = 0;
				}
				if (cid == 0) {
					return -2;
				}

				// To generate Account No
				int number = cid + 3333;
				customerAccountNo = "0043201" + number;

				// To check customer saving account already exist or not
				Query CheckTypequery = entityManager.createNativeQuery(accountQueryConstant.getSavingTypeCheck())
						.setParameter("customerId", customerId)
						.setParameter("customerAccountType", customerAccountType);
				int id = 0;
				try {
					id = (Integer) CheckTypequery.getSingleResult();
				} catch (NoResultException e) {
					id = 0;
				}

				// To check Customer status is Active or Inactive
				Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
						.setParameter("customerId", customerId);
				String checkStatus = (String) statusquery.getSingleResult();

				if ((id == 0) && (cid == customerId) && (checkStatus.equalsIgnoreCase("Active"))) {
					if (customerBalance >= 1000) {
						// Insert Data into customer_Account
						Query insertAccountAuery = entityManager
								.createNativeQuery(accountQueryConstant.getInserAccountData())
								.setParameter("customerId", customerId)
								.setParameter("customerAccountNo", customerAccountNo)
								.setParameter("customerAccountType", customerAccountType)
								.setParameter("customerBalance", customerBalance);
						status = insertAccountAuery.executeUpdate();

						// Insert Data into customer_Transactional
						Query insertTransactionAuery = entityManager
								.createNativeQuery(accountQueryConstant.getInsertTransactionData())
								.setParameter("customerId", customerId)
								.setParameter("customerAccountNo", customerAccountNo)
								.setParameter("customerTransactionType", customerTransactionType)
								.setParameter("customerBalance", customerBalance);
						status = insertTransactionAuery.executeUpdate();
						return status;
					} // end condition check if
					else {
						return -1;
					}
				} // end if
				else {
					return -2;
				} // else end
			} // end try
			catch (Exception e) {
				logger.error("Error while creating customer account: " + e);
			}
		} // end elseif
		else {
			return status;
		}
		logger.info("End AccountDaoImpl class -> accountOpen()");
		return status;
	}// End accountOpen()

	// To deposite Amount
	@Transactional
	@Override
	public Integer depositAmount(CustomerAccount deposit) {
		logger.info("Start AccountDaoImpl class -> depositAmount()");
		int status = 0;
		int customerId = deposit.getCustomerId();
		String customerAccountType = deposit.getCustomerAccountType();
		int customerTransactionAmount = deposit.getCustomerTransactionAmount();
		String customerTransactionType = "Deposit";
		try {
			// To check inserted CustomerId is same or not
			int cid = 0;
			try {
				cid = (Integer) entityManager.createNativeQuery(accountQueryConstant.getFetchId())
						.setParameter("customerId", customerId).getSingleResult();
			} catch (NoResultException e) {
				cid = 0;
			}
			if (cid == 0) {
				return -2;
			}

			// To fetch customer current total balance
			Query balancequery = entityManager.createNativeQuery(accountQueryConstant.getCustomerBalance())
					.setParameter("customerId", customerId).setParameter("customerAccountType", customerAccountType);
			Iterator itr = balancequery.getResultList().iterator();
			int totalBalance = 0;
			while (itr.hasNext()) {
				totalBalance = (int) itr.next();
			}

			// To fetch Account No
			Query fetchaccountNo = entityManager.createNativeQuery(accountQueryConstant.getCheckAccountNo())
					.setParameter("customerId", customerId).setParameter("customerAccountType", customerAccountType);
			String accountNo = (String) fetchaccountNo.getSingleResult();

			// To check Customer status is Active or Inactive
			Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
					.setParameter("customerId", customerId);
			String checkStatus = (String) statusquery.getSingleResult();

			// To check Balance total
			int total = totalBalance + customerTransactionAmount;

			if ((cid == customerId) && (checkStatus.equalsIgnoreCase("Active"))
					&& (customerAccountType.equalsIgnoreCase("saving"))
					|| (customerAccountType.equalsIgnoreCase("current"))) {
				if (customerTransactionAmount > 0) {
					// Update account table while depositing amount
					Query query = entityManager.createNativeQuery(accountQueryConstant.getUpdateAccount())
							.setParameter("customerId", customerId).setParameter("customerBalance", total)
							.setParameter("customerAccountNo", accountNo)
							.setParameter("customerAccountType", customerAccountType);
					status = query.executeUpdate();

					// insert new transaction in transaction table while depositing amount
					Query query1 = entityManager.createNativeQuery(accountQueryConstant.getTransactionHistory())
							.setParameter("customerId", customerId).setParameter("customerAccountNo", accountNo)
							.setParameter("customerTransactionType", customerTransactionType)
							.setParameter("customerTransactionAmount", customerTransactionAmount)
							.setParameter("customerBalance", total);
					status = query1.executeUpdate();
					return status;
				} else {
					return -1;
				}
			} else {
				return -2;
			}
		} // end try
		catch (Exception e) {
			logger.error("Error while depositing amount: ", e);
		}
		logger.info("End AccountDaoImpl class -> depositAmount()");
		return status;
	}// End depositAmount()

	// To withdraw Amount
	@Transactional
	@Override
	public Integer withdrawAmount(CustomerAccount withdraw) {
		logger.info("Start AccountDaoImpl class -> withdrawAmount()");
		int customerId = withdraw.getCustomerId();
		String customerAccountType = withdraw.getCustomerAccountType();
		String customerTransactionType = "Withdraw";
		int customerTransactionAmount = withdraw.getCustomerTransactionAmount();
		try {
			// To check inserted CustomerId is same or not
			int cid = 0;
			try {
				cid = (Integer) entityManager.createNativeQuery(accountQueryConstant.getFetchId())
						.setParameter("customerId", customerId).getSingleResult();
			} catch (NoResultException e) {
				cid = 0;
			}
			if (cid == 0) {
				return -2;
			}

			// To fetch customer current total balance
			Query balancequery = entityManager.createNativeQuery(accountQueryConstant.getCustomerBalance())
					.setParameter("customerId", customerId).setParameter("customerAccountType", customerAccountType);
			Iterator itr = balancequery.getResultList().iterator();
			int totalBalance = 0;
			while (itr.hasNext()) {
				totalBalance = (int) itr.next();
			}

			// To fetch Account No
			Query fetchaccountNo = entityManager.createNativeQuery(accountQueryConstant.getCheckAccountNo())
					.setParameter("customerId", customerId).setParameter("customerAccountType", customerAccountType);
			String accountNo = (String) fetchaccountNo.getSingleResult();

			// To check Customer status is Active or Inactive
			Query statusquery = entityManager.createNativeQuery(queryConstant.getCheckStatus())
					.setParameter("customerId", customerId);
			String checkStatus = (String) statusquery.getSingleResult();

			// To check Balance Difference
			int difference = totalBalance - customerTransactionAmount;

			String accountType = "";

			if ((customerTransactionAmount > 0) && (difference >= 5000)
					&& (customerAccountType.equalsIgnoreCase("saving"))) {
				accountType = "saving";
			} else if (((customerTransactionAmount) > 0) && (difference >= 1000)
					&& (customerAccountType.equalsIgnoreCase("current"))) {
				accountType = "current";
			} else {
				accountType = "";
			}

			if ((cid == customerId) && (checkStatus.equalsIgnoreCase("Active"))
					&& (accountType.equalsIgnoreCase("saving")) || (accountType.equalsIgnoreCase("current"))) {
				Query query = entityManager.createNativeQuery(accountQueryConstant.getUpdateAccount())
						.setParameter("customerId", customerId).setParameter("customerBalance", difference)
						.setParameter("customerAccountNo", accountNo)
						.setParameter("customerAccountType", customerAccountType);
				status = query.executeUpdate();

				Query query1 = entityManager.createNativeQuery(accountQueryConstant.getTransactionHistory())
						.setParameter("customerId", customerId).setParameter("customerAccountNo", accountNo)
						.setParameter("customerTransactionType", customerTransactionType)
						.setParameter("customerTransactionAmount", customerTransactionAmount)
						.setParameter("customerBalance", difference);
				status = query1.executeUpdate();
				return status;
			} else {
				return -2;
			}
		} // end try
		catch (Exception e) {
			logger.error("Error while Withdrawing amount: ", e);
		}
		logger.info("End AccountDaoImpl class -> withdrawAmount()");
		return status;
	}// End depositAmount()

	// To fetch latest created account details
	@Override
	public CustomerAccount getLatestAccount() {
		logger.info("Start AccountDaoImpl class -> getLatestAccount()");
		CustomerAccount customerAccountData = new CustomerAccount();
		try {
			Query queryData = entityManager.createNativeQuery(accountQueryConstant.getLatesCustomerAccountDetail(),
					"CUSTOMER_ACCOUNT_DATA");
			customerAccountData = (CustomerAccount) queryData.getSingleResult();
			return customerAccountData;
		} // end try
		catch (Exception e) {
			logger.error("Error while to get latest created account details: ", e);
		}
		logger.info("End AccountDaoImpl class -> getLatestAccount()");
		return customerAccountData;
	}

	// To fetch latest created transaction details
	@Override
	public CustomerTransaction getLatestTransaction() {
		logger.info("Start AccountDaoImpl class -> getLatestTransaction()");
		CustomerTransaction customerTransactionData = new CustomerTransaction();
		try {
			Query queryData = entityManager.createNativeQuery(accountQueryConstant.getLatesTransactionalDetail(),
					"CUSTOMER_TRANSACTION_DATA");
			customerTransactionData = (CustomerTransaction) queryData.getSingleResult();
			return customerTransactionData;
		} // end try
		catch (Exception e) {
			logger.error("Error while to get latest created transaction details:", e);
		}
		logger.info("End AccountDaoImpl class -> getLatestTransaction()");
		return customerTransactionData;
	}
}// end AccountDaoImpl class