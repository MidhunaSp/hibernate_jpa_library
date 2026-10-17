package com.library.main;

import com.library.config.AppConfig;
import com.library.model.Author;
import com.library.service.AuthorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AuthorController {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AuthorService authorService = context.getBean(AuthorService.class);

        authorService.insertAuthor("J.K. Rowling", "UK");
        authorService.insertAuthor("J.R.R. Tolkien", "UK");
        authorService.insertAuthor("Dan Brown", "USA");
        authorService.insertAuthor("Stephen Hawking", "UK");
        authorService.insertAuthor("George Orwell", "UK");
        authorService.insertAuthor("Yuval Noah Harari", "Israel");

        // findById
        Author author = authorService.findById(4L);
        System.out.println("Author by id: " + author);

        // findAll
        System.out.println("---- All authors ----");
        List<Author> authors = authorService.findAll();
        authors.forEach(System.out::println);
    }
}