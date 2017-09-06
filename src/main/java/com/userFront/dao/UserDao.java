package com.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.userFront.domain.User;

/**
 * Created by @author sduong on 05.09.2017
 *
 */
public interface UserDao extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}
