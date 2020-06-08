package com.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

	@GetMapping("/redirectToLinkedIn")
	public String redirectOut(){
		System.out.println("In the redirect controller");
		return "redirect:https://www.linkedin.com";
	}
}
