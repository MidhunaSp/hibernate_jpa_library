package com.library.service;

import com.library.exception.ResourceNotFoundException;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void insertAuthor(String name, String country) {
        Author author = new Author(name, country);
        authorRepository.insertAuthor(author);
    }

    public Author findById(Long id) {
        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("author id invalid: " + id);
        return optional.get();
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}