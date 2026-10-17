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

    public void insertMember(Member member) {
        entityManager.persist(member);
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    // task 1
    public List<Member> findAll() {
        return entityManager
                .createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
