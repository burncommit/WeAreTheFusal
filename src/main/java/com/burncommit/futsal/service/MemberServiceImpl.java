package com.burncommit.futsal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.burncommit.futsal.domain.Member;
import com.burncommit.futsal.dto.MemberResponseDTO;
import com.burncommit.futsal.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service			// 내가 서비스다
@RequiredArgsConstructor	// 밑에 MemberRepository의 생성자를 쓰지 않기 위해서
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
   

    @Override
    public Long join(Member member) throws IllegalAccessException {
    	
    	
    	
    	validateMemberCheck(member);
    	memberRepository.save(member);
        return member.getId();
    }
    
    private void validateMemberCheck(Member member) throws IllegalAccessException {
    	List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
    	
    	if( !findMembers.isEmpty() ) {
			throw new IllegalAccessException("이미 존재하는 회원입니다.");
		}
    	
    }
    
    @Override
    public List<Member> findMembers(){
		return memberRepository.findAll();
	}
    
    
   
}
