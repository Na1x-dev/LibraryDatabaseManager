package com.example.demo.services.isbn;

import com.example.demo.models.Book;
import com.example.demo.models.Isbn;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.isbn.IsbnJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IsbnServiceImpl implements IsbnService {

    @Autowired
    IsbnJpaRepository isbnRepository;

    @Override
    public Isbn create(Isbn isbn) {
        isbn.setIsbnNumber(isbn.getIsbnNumber().toLowerCase());
        return isbnRepository.save(isbn);
    }

    @Override
    public List<Isbn> readAll() {
        return isbnRepository.findAll();
    }

    @Override
    public Isbn readByIsbnNumber(String isbnNumber) {
        return isbnRepository.findByIsbnNumber(isbnNumber.toLowerCase());
    }
}
