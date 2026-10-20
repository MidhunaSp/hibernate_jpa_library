package com.library.repository;

import com.library.model.LibraryEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibraryEventRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertEvent(LibraryEvent event) {
        entityManager.persist(event);
    }

    public Optional<LibraryEvent> findById(Long id) {
        return Optional.ofNullable(entityManager.find(LibraryEvent.class, id));
    }

    public List<LibraryEvent> findAll() {
        String jpql = "select e from LibraryEvent e";
        return entityManager.createQuery(jpql, LibraryEvent.class)
                .getResultList();
    }
}