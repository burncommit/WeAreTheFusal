package com.burncommit.futsal.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.burncommit.futsal.domain.Member;
import com.burncommit.futsal.dto.MemberResponseDTO;
import com.burncommit.futsal.dto.MemberSaveRequestDTO;

public interface MemberService {

    Long join(Member member) throws IllegalAccessException;
    
    /**
     * 회원 목록 조회
     * @return 회원 정보 목록
     */
    List<Member> findMembers();
}