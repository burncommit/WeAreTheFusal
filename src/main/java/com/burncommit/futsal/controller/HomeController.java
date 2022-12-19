package com.burncommit.futsal.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		
//		LocalDate now = LocalDate.now();
		List<LocalDate> now = new ArrayList<LocalDate>();
		for(int i = 0; i <7; i++) {
			now.add(LocalDate.now().plusDays(i));
		}
		
		model.addAttribute("now", now);
		
		return "home";
	}
}
