package com.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.userFront.domain.security.Role;

/**
 * Created by @author sduong on 06.09.2017
 *
 */
public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}
