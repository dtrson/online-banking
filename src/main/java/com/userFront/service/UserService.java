package com.userFront.service;

import java.util.Set;

import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;

public interface UserService {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);

	boolean checkEmailExists(String email);
	
	boolean checkUsernameExists(String username);
	
	void save(User user);
	
	User createUser(User user, Set<UserRole> userRoles);
}
