package com.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.userFront.domain.SavingsAccount;

/**
 * Created by @author sduong on 06.09.2017
 *
 */
public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
	SavingsAccount findByAccountNumber(int accountNumber);
}
