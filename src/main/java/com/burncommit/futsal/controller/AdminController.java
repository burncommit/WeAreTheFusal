package com.burncommit.futsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("adminPage")
	public String adminHome() {
		return "/admin/adminHome";
	}
	
	@GetMapping("userList")
	public String userList() {
		return "/admin/userList";
	}
}
