package com.userFront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userFront.dao.PrimaryAccountDao;
import com.userFront.dao.SavingsAccountDao;
import com.userFront.domain.PrimaryAccount;
import com.userFront.domain.PrimaryTransaction;
import com.userFront.domain.SavingsAccount;
import com.userFront.domain.SavingsTransaction;
import com.userFront.domain.User;
import com.userFront.service.AccountService;
import com.userFront.service.UserService;

/**
 * Created by @author sduong on 06.09.2017
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber = 11223145;
	
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired 
	private UserService userService;
	
	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountDao.save(primaryAccount);
		
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());
		
		savingsAccountDao.save(savingsAccount);
		
		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
	}


	@Override
	public void deposit(String accountType, double amount, Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")){
			
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			BigDecimal newBalance = primaryAccount.getAccountBalance().add(new BigDecimal(amount));
			primaryAccount.setAccountBalance(newBalance);
			primaryAccountDao.save(primaryAccount);
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(
					new Date(), 
					"Deposit to Primary Account",
					"Account",
					"Finished", 
					amount, 
					primaryAccount.getAccountBalance(), 
					primaryAccount);
			
			
		}else if(accountType.equalsIgnoreCase("Savings")){
			
			SavingsAccount savingsAccount = user.getSavingsAccount();
			BigDecimal newBalance = savingsAccount.getAccountBalance().add(new BigDecimal(amount));
			savingsAccount.setAccountBalance(newBalance);
			savingsAccountDao.save(savingsAccount);
			
			SavingsTransaction savingsTransaction = new SavingsTransaction(
					new Date(), 
					"Deposit to Savings Account",
					"Account",
					"Finished", 
					amount, 
					savingsAccount.getAccountBalance(), 
					savingsAccount);
		
		}else{
			
		}
		
	}
	
	
	private int accountGen(){
		return ++nextAccountNumber;
	}

}
