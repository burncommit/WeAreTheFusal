
package com.burncommit.futsal.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.burncommit.futsal.domain.Member;



public interface MemberRepository extends JpaRepository<Member, Long> {

	
	
	
	 List<Member> findByEmail(String email);
	 
//	 Member findByEmail(String email);
	 
	
	 /**
	     * 유효성 검사 - 중복 체크
	     * @param email 회원 이메일
	     * @return
	     */
	    boolean existsByEmail(String email);
	    
	    
}
