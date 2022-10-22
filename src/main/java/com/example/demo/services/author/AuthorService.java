package com.example.demo.services.author;

import com.example.demo.models.Author;
import com.example.demo.models.Book;

import java.util.List;

public interface AuthorService {
    Author create(Author author);

    List<Author> readAll();


    Author readByAuthorName(String authorName);
}
