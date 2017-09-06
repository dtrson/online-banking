package com.userFront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userFront.dao.PrimaryAccountDao;
import com.userFront.dao.PrimaryTransactionDao;
import com.userFront.dao.SavingTransactionDao;
import com.userFront.dao.SavingsAccountDao;
import com.userFront.domain.PrimaryAccount;
import com.userFront.domain.PrimaryTransaction;
import com.userFront.domain.SavingsAccount;
import com.userFront.domain.SavingsTransaction;
import com.userFront.domain.User;
import com.userFront.service.TransactionService;
import com.userFront.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;
	
	@Autowired
	private SavingTransactionDao savingsTransactionDao;
	
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	
	public List<PrimaryTransaction> findPrimaryTransactionList(String username){
		
		User user = userService.findByUsername(username);
		List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();
		
		return primaryTransactionList;
	}
	
	public List<SavingsTransaction> findSavingsTransactionList(String username){
		
		User user = userService.findByUsername(username);
		List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
		
		return savingsTransactionList;
	}
	
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction){
		primaryTransactionDao.save(primaryTransaction);
	}
	
	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction){
		savingsTransactionDao.save(savingsTransaction);
	}

	@Override
	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
		
	}

	@Override
	public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
		
		if(transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")){
			
			BigDecimal transferAmount = new BigDecimal(amount);
			
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(transferAmount));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(transferAmount));
			
			primaryAccountDao.save(primaryAccount);
			savingsAccountDao.save(savingsAccount);
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(
					new Date(), 
					"Between accounts transfer from "+ transferFrom +" Account to "+ transferTo + " Account",
					"Account",
					"Finished", 
					Double.parseDouble(amount), 
					primaryAccount.getAccountBalance(), 
					primaryAccount);
			
			SavingsTransaction savingsTransaction = new SavingsTransaction(
					new Date(), 
					"Between accounts transfer from "+ transferFrom +" Account to "+ transferTo + " Account",
					"Account",
					"Finished", 
					Double.parseDouble(amount), 
					savingsAccount.getAccountBalance(), 
					savingsAccount);
			
			savingsTransactionDao.save(savingsTransaction);
			
			primaryTransactionDao.save(primaryTransaction);
			
		} else if(transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")){
			
			BigDecimal transferAmount = new BigDecimal(amount);
			
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(transferAmount));
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(transferAmount));

			
			primaryAccountDao.save(primaryAccount);
			savingsAccountDao.save(savingsAccount);
			
			SavingsTransaction savingsTransaction = new SavingsTransaction(
					new Date(), 
					"Between accounts transfer from "+ transferFrom +" Account to "+ transferTo + " Account",
					"Account",
					"Finished", 
					Double.parseDouble(amount), 
					savingsAccount.getAccountBalance(), 
					savingsAccount);
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(
					new Date(), 
					"Between accounts transfer from "+ transferFrom +" Account to "+ transferTo + " Account",
					"Account",
					"Finished", 
					Double.parseDouble(amount), 
					primaryAccount.getAccountBalance(), 
					primaryAccount);
			
			savingsTransactionDao.save(savingsTransaction);
			primaryTransactionDao.save(primaryTransaction);
			
			
		}else{
			throw new Exception("Invalid Transfer!!!");
		}
		
	}
	
	
}
