package com.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.beans.User;
import com.test.repository.UserRepository;


@Controller
public class RegistrationController {
	/**
	 * RegistrationController is used to store user info hence
	 * why we use the annotation @PostMapping to protect the user's info
	 * 
	 * @Autowired invokes the UserRepository dependency injection
	 * 
	 * @ModelAttribute is bound to the user object and is mapped with "newuser"
	 * to the view
	 *
	 */
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/registeruser")
	public String registerUser(@ModelAttribute("newuser") User user, Model model) {
		System.out.println("in the register controller");
		userRepository.save(user);
		model.addAttribute("dataSaved", "User saved successfully");
		return "login";
	}
}
