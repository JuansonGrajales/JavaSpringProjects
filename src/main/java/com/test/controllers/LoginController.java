package com.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.beans.Login;
import com.test.beans.User;
import com.test.hplus.exceptions.ApplicationException;
import com.test.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public String login(@ModelAttribute("login") Login login) {
		User user = userRepository.searchByName(login.getUsername());
		if(user==null) {
			throw new ApplicationException("User not found");
		}
		
		return "search";
	}
	
	@ExceptionHandler(ApplicationException.class)
	public String handleException() {
		System.out.println("in exception handler in log in controller");
		return "error";
	}
}
