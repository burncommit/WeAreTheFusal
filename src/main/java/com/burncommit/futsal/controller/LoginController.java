package com.burncommit.futsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}
	
	@GetMapping("/addUserForm")
	public String addUserForm(Model model) {
		
		return "/user/addUserForm";
	}
}
