package com.library.repository;

import com.library.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // task 1: save -> insert a member
    public void insertAuthor(Author author) {
        entityManager.persist(author);
    }

    // task 1: findById -> read a single member by id
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    // task 1: findAll -> read all members
    public List<Author> findAll() {
        String jpql = "select a from Author a";
        return entityManager.createQuery(jpql, Author.class)
                .getResultList();
    }
}