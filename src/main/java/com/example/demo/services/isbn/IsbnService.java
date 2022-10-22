package com.example.demo.services.isbn;

import com.example.demo.models.Book;
import com.example.demo.models.Isbn;

import java.util.List;

public interface IsbnService {
    Isbn create(Isbn isbn);

    List<Isbn> readAll();

    Isbn readByIsbnNumber(String isbnNumber);
}
