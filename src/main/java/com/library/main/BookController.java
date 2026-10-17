package com.library.main;

import com.library.config.AppConfig;
import com.library.enums.Genre;
import com.library.enums.MembershipType;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class BookController {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);
        MemberService memberService = context.getBean(MemberService.class);

        memberService.insertMember("Mahi", "mahi@gmail.com", MembershipType.PREMIUM);
        memberService.insertMember("Luna", "luna@gmail.com", MembershipType.STANDARD);

        // task 1: save
//        bookService.saveBook(1L, "Harry Potter and the Philosopher's Stone", Genre.FICTION, 1997);
//        bookService.saveBook(2L, "The Hobbit", Genre.FANTASY, 1937);
//        bookService.saveBook(4L, "A Brief History of Time", Genre.SCIENCE, 1988);
//        bookService.saveBook(5L, "1984", Genre.DYSTOPIAN, 1949);
//        bookService.saveBook(6L, "Sapiens: A Brief History of Humankind", Genre.HISTORY, 2011);
//        bookService.saveBook(3L, "The Da Vinci Code", Genre.THRILLER, 2003);

        bookService.borrowBook(2L, 1L);
        bookService.borrowBook(5L, 2L);

        // task 1: findById
        Book book = bookService.findById(5L);
        System.out.println("Book by id: " + book);

        // task 1: findAll (with Author and Member info)
        System.out.println("---- All books ----");
        List<Book> books = bookService.findAll();
        books.forEach(System.out::println);
    }
}