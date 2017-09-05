package com.userFront.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userFront.domain.User;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(){
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(Model model){
		
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model){
		
//		if(userService.checkUserExists(user.getUsername(),user.getEmail())){
//			
//			if(userService.checkEmailExists(user.getEmail())){
//				model.addAttribute("emailExists", true);
//			}
//			
//			if(userService.checkUsernameExists(user.getUsername())){
//				model.addAttribute("usernameExists", true);
//			}
//			
//			return "signup";
//		}
//		else{
//			Set<UserRole> userRoles = new HashSet<>();
//			userRoles.add(new UserRole(user, roleDao.findByName("USER")));
//			userService.createUser(user,userRoles);
//			
//			return "redirect:/";
//		}
		
		return "signup";

	}
	
}