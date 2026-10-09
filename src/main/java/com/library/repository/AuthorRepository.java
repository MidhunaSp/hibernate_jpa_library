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

    public void insertAuthor(Author author) {
        entityManager.persist(author);
    }

    public Optional<Author> findById(Long id){
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }


    public List<Author> findAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }
}
