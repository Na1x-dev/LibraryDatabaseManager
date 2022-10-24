package com.example.demo.services.book;

import com.example.demo.models.Book;
import com.example.demo.models.User;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.role.RoleJpaRepository;
import com.example.demo.repositories.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookJpaRepository bookRepository;

    @Override
    public Book create(Book book) {
        book.setTitle(book.getTitle().toLowerCase().trim());
        return bookRepository.save(book);
    }

    @Override
    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book readByBookTitle(String title) {
        return bookRepository.findBookByTitle(title.toLowerCase().trim());
    }

    @Override
    public Book readByBookTitleAndAuthorAndPublisherAndLanguage(String title, String authorName, String publisherTitle, String languageName) {
        return bookRepository.findBookByTitleAndAuthorAuthorNameAndPublisherPublisherTitleAndLanguageLanguageName(title, authorName, publisherTitle, languageName);
    }
}
