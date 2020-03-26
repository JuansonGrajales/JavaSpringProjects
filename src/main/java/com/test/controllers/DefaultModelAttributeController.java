package com.test.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.test.beans.Login;
import com.test.beans.User;

@ControllerAdvice
public class DefaultModelAttributeController {

	/**
	 * The @ModelAttribute attached to the register.jsp needs to find an object
	 * corresponding to the ModelAttribute. If it doesn't, it will not render the
	 * page correctly. Therefore getDefaultUser will provide default values for the
	 * user bean
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
		return Arrays.asList(new String[] { "Male", "Female", "Other" });
	}
	

	/**
	 * A @ModelAttribute to render default values for Login
	 *
	 */
	@ModelAttribute("login")
	public Login getDefaultLogin() {
		return new Login();
	}
}
