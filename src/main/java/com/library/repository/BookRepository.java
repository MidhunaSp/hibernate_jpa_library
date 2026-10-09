package com.library.repository;

import com.library.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // task 1: save(book) -> insert
    public void save(Book book){
        entityManager.persist(book);
    }

    // task 1: findById(id) -> read single Book by its id
    public Optional<Book> findById(Long id){
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    // task 1: findAll() -> read all books with Author and Member info
    public List<Book> findAll(){
        String jpql = """
                select b
                from Book b
                join fetch b.author
                left join fetch b.borrowedBy
                """;
        return entityManager.createQuery(jpql, Book.class)
                .getResultList();
    }
}
