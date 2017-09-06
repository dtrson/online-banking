package com.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.userFront.domain.PrimaryAccount;

/**
 * Created by @author sduong on 06.09.2017
 *
 */
public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
	PrimaryAccount findByAccountNumber(int accountNumber);
}
