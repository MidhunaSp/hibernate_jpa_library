package com.library.service;

import com.library.enums.MembershipType;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Member;
import com.library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Member findById(Long id) {
        Optional<Member> optional = memberRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("member id invalid: " + id);
        return optional.get();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
