package com.burncommit.futsal.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burncommit.futsal.domain.Member;
import com.burncommit.futsal.domain.Role;
import com.burncommit.futsal.repository.MemberRepository;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	
    private final MemberRepository memberRepository;
	

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

    	List<Member> member = Optional.ofNullable(memberRepository.findByEmail(email))
    			.orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

//    	Member member = memberRepository.findByEmaill(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

//    	List<Member> members = memberRepository.findByEmail(email);
//    	Member member = null;
//    	for(Member m : members) {
//    		member = m;
//    	}
//    	List<Member> member = memberRepository.findByEmail(email);
//    	if(member == null) {
//    		throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
//    		
//    	}
    	return toUserDetails(member);
    	
    }

    private UserDetails toUserDetails(List<Member> member) {
    	if(member.size() != 0 ) {
    		return User.builder()
    				.username(member.get(0).getEmail())
    				.password(member.get(0).getPassword())
    				.authorities(new SimpleGrantedAuthority(member.get(0).getRole().toString()))
    				.build();
    		
    	}
    	System.out.println("이멜이 없다");
    	throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
    	
    }
}
