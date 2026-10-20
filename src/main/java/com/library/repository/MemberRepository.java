package com.library.repository;

import com.library.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // task 1: save -> insert a member
    public void insertMember(Member member) {
        entityManager.persist(member);
    }

    // task 1: findById -> read a single member by id and validate memberId
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    // task 1: findAll -> read all members
    public List<Member> findAll() {
        String jpql = "select m from Member m";
        return entityManager.createQuery(jpql, Member.class)
                .getResultList();
    }
}