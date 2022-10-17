package com.example.demo.services.book;

import com.example.demo.models.Book;
import java.util.List;

public interface BookService {
    void create(Book book);

    List<Book> readAll();

}