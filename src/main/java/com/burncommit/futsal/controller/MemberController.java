package com.burncommit.futsal.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.burncommit.futsal.domain.Member;
import com.burncommit.futsal.domain.Role;
import com.burncommit.futsal.dto.MemberResponseDTO;
import com.burncommit.futsal.dto.MemberSaveRequestDTO;
import com.burncommit.futsal.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller			// 해당 클래스가 컨트롤러임을 알리고 bean으로 등록하기 위함
@RequiredArgsConstructor	// 나중에 의존관계 관련하여 필요한 어노테이션
public class MemberController {
	
	private final MemberService memberService;
	 private final PasswordEncoder passwordEncoder;
	

	 @GetMapping("/login")
	 public String login() {
		 return "members/loginForm";
	 }
	 @PostMapping("/loginSuccess")
	 public String loginSuccess() {
		 return "redirect:/";
	 }
    
    
    
    @GetMapping("/members/new")
    public String createMemberForm(Model model) {
    	model.addAttribute("memberSaveRequestDTO", new MemberSaveRequestDTO());
        return "members/createMemberForm";
    }
    
    @PostMapping("/member/new")
    public String createMember(@Valid MemberSaveRequestDTO memberSaveRequestDTO, BindingResult bindingResult) throws IllegalAccessException {
    	if(bindingResult.hasErrors()) {
    		 return "members/createMemberForm";
    	}
    	memberSaveRequestDTO.setPassword(passwordEncoder.encode(memberSaveRequestDTO.getPassword()));
    	
    	Member member = new Member();
    	member.setEmail(memberSaveRequestDTO.getEmail());
    	member.setUsername(memberSaveRequestDTO.getUsername());
    	member.setPassword(memberSaveRequestDTO.getPassword());
    	member.setRole(Role.ROLE_USER);
    			
    	memberService.join(member);
    	return "redirect:/";
    	
    }
    
    
}