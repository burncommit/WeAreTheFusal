package com.burncommit.futsal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.burncommit.futsal.domain.Member;
import com.burncommit.futsal.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final MemberService memberService;

	@GetMapping("adminPage")
	public String adminHome() {
		return "/admin/adminHome";
	}
	
	@GetMapping("/members")
    public String members(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "admin/userList";
    }
}
