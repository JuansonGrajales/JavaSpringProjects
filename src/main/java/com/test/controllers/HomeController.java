package com.test.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.test.beans.User;

/**
 * HomeController class navigates the user to the different
 * links on the navigation bar
 */

@Controller
public class HomeController {

	@GetMapping("/home")	
	public String goHome() {
		System.out.println("in home controller");
		return "index";
	}
	
	@GetMapping("/goToSearch")	
	public String goToSearch() {
		System.out.println("going to search page");
		return "search";
	}
	
	@GetMapping("/goToLogin")	
	public String goToLogin() {
		System.out.println("going to login page");
		return "login";
	}
	
	@GetMapping("/goToRegistration")	
	public String goToRegistration() {
		System.out.println("going to registration page");
		return "register";
	}

/**
 * The @ModelAttribute attached to the register.jsp needs to find 
 * an object corresponding to the ModelAttribute. If it doesn't, it will
 * not render the page correctly. Therefore getDefaultUser will provide default 
 * values for the user bean
 *
 */
	@ModelAttribute("newuser")
	public User getDefaultUser() {
		return new User();
	}
	
	/**
	 * A @ModelAttribute to render default values of the gender drop down option
	 *
	 */
	@ModelAttribute("genderItems")
	public List<String> getGenderItems() {
		return Arrays.asList( new String[] {"Male", "Female", "Other"});
	}
}
