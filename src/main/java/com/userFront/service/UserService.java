package com.userFront.service;

import java.util.Set;

import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;

/**
 * Created by @author sduong on 05.09.2017
 *
 */
public interface UserService {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);

	boolean checkEmailExists(String email);
	
	boolean checkUsernameExists(String username);
	
	void save(User user);
	
	User createUser(User user, Set<UserRole> userRoles);
	
	User saveUser(User user);
}
