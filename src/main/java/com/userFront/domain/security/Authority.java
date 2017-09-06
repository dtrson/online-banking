package com.userFront.domain.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by @author sduong on 06.09.2017
 *
 */
public class Authority implements GrantedAuthority{
	
	private final String authority;
	
	public Authority(String authority){
		this.authority = authority;
	}
	
	@Override
	public String getAuthority(){
		return authority;
	}
}
