package com.userFront.service;

import java.security.Principal;
import com.userFront.domain.PrimaryAccount;
import com.userFront.domain.SavingsAccount;

/**
 * Created by @author sduong on 06.09.2017
 *
 */
public interface AccountService {
	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	void deposit(String accountType, double amount, Principal principal);
}
