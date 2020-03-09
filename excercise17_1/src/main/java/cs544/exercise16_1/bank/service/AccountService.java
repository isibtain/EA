package cs544.exercise16_1.bank.service;

import java.util.Collection;

import org.hibernate.SessionFactory;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import cs544.exercise16_1.bank.dao.AccountDAO;
import cs544.exercise16_1.bank.dao.IAccountDAO;
import cs544.exercise16_1.bank.domain.Account;
import cs544.exercise16_1.bank.domain.Customer;
import cs544.exercise16_1.bank.jms.IJMSSender;
import cs544.exercise16_1.bank.jms.JMSSender;
//import cs544.exercise16_1.bank.jms.JMSSender;
import cs544.exercise16_1.bank.logging.ILogger;
//import cs544.exercise16_1.bank.logging.Logger;
import cs544.exercise16_1.bank.logging.Logger;


public class AccountService implements IAccountService {
	private SessionFactory sessionFactory;
	//Transaction tx = null;
	private IAccountDAO accountDAO;
	private ICurrencyConverter currencyConverter;
	private IJMSSender jmsSender;
	private ILogger logger;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;		
	}
	
	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public void setCurrencyConverter(ICurrencyConverter currencyConverter) {
		this.currencyConverter = currencyConverter;
	}
	
	public void setJmsSender(IJMSSender jmsSender) {
		this.jmsSender = jmsSender;
	}
	 
	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
	public AccountService(){
		currencyConverter= new CurrencyConverter();
		jmsSender =  new JMSSender();
		logger = new Logger();
	}
    
	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);		
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}
   
	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public void deposit(long accountNumber, double amount) {
		//tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		//tx.commit();
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}
    
	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public Account getAccount(long accountNumber) {
		//tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		//tx.commit();
		return account;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public Collection<Account> getAllAccounts() {
		//tx = sf.getCurrentSession().beginTransaction();
		Collection<Account> accounts = accountDAO.getAccounts();
		//tx.commit();
		return accounts;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public void withdraw(long accountNumber, double amount) {
		//tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		//tx.commit();
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public void depositEuros(long accountNumber, double amount) {
		//tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		//tx.commit();
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public void withdrawEuros(long accountNumber, double amount) {
		//tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		//tx.commit();
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		//tx = sf.getCurrentSession().beginTransaction();
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		//tx.commit();
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
