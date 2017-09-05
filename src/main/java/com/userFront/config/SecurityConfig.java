package com.userFront.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private static final String SALT = "salt"; // Salt should be protected carefully
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
    
    //list of methods/folders that can be accessed without spring security
    private static final String[] PUBLIC_MATCHERS = {
    	"/webjars/**",
    	"/css/**",
    	"/js/**",
    	"/images/**",
    	"/",
    	"/about/**",
    	"/contact/**",
    	"/error/**/*",
    	"/console/**",
    	"/signup"
    };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	http
	    	.authorizeRequests()
//    		.antMatchers("/**")
	    	.antMatchers(PUBLIC_MATCHERS)
	    	.permitAll().anyRequest().authenticated();
    	
    	http
    		.csrf().disable().cors().disable()
    		.formLogin().failureUrl("/index?error").defaultSuccessUrl("/userFront").loginPage("/index").permitAll();
    }
}
