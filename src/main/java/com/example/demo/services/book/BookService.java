package com.example.demo.services.book;

import com.example.demo.models.Book;

import java.util.List;

public interface BookService {
    Book create(Book book);

    List<Book> readAll();


    Book readByBookTitle(String title);

    Book readByBookTitleAndAuthorAndPublisherAndLanguage(String title, String authorName, String publisherTitle, String languageName);

    boolean update(Book book, Long bookId);

    boolean delete(Long bookId);

    Book readById(Long bookId);
}
