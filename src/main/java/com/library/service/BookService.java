package com.library.service;

import com.library.enums.BookStatus;
import com.library.enums.Genre;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
    }

    // task 1: save(book) -> insert
    @Transactional
    public void saveBook(Long authorId, String title, Genre genre,int publishedYear){

        // Step 1: fetch Author obj using given authorId
        Optional<Author> optional = authorRepository.findById(authorId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("author id invalid");

        Author author = optional.get();

        // Step 2: Prepare Book object (new book is AVAILABLE, borrowedBy stays null)
        Book book = new Book(title, genre, BookStatus.AVAILABLE, publishedYear);

        // Step 3: Attach author obj to book obj
        book.setAuthor(author);

        // Step 4: Add book object
        bookRepository.save(book);
    }

    // task 1: findById(id) -> read single book
    public Book findById(Long id){
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("book id invalid");

        return optional.get();
    }

    // task 1: findAll() -> read all books with Author nd Member info
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional
    public void borrowBook(Long bookId, Long memberId) {
        Book book = findById(bookId);

        Optional<Member> optional = memberRepository.findById(memberId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("member id invalid");

        book.setBorrowedBy(optional.get());
        book.setStatus(BookStatus.BORROWED);
    }
}
