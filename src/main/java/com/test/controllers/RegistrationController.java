package com.test.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	 * @Valid is a signal that i've used javax annotations (User.class) for validation
	 * and whenever @ModelAttribute start to perform data binding for the user object, 
	 * make sure to first run the validation
	 * 
	 * Once spring runs the validation, if some of the validations fail, it will populate
	 * the errors in a special object, Binding Result. This object needs to be placed immediately
	 * next to the @ModelAttribute
	 * 
	 * @InitBinder 
	 */
	
	@Autowired
	private UserRepository userRepository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
	}

	@PostMapping("/registeruser")
	public String registerUser(@Valid @ModelAttribute("newuser") User user, BindingResult result, Model model) {
		System.out.println("in the register controller");
		System.out.println(user.getDateOfBirth());
		//return the user back to the register page if there are errors to the input
		if(result.hasErrors()) {
			return "register";
		}
		userRepository.save(user);
		model.addAttribute("dataSaved", "User saved successfully");
		return "login";
	}
}
