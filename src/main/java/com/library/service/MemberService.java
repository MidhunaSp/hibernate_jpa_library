package com.library.service;

import com.library.enums.MembershipType;
import com.library.model.Member;
import com.library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void insertMember(String name, String email, MembershipType membershipType) {
        Member member = new Member(name, email, membershipType);
        memberRepository.insertMember(member);
    }
}
