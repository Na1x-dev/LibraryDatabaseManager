package com.example.demo.services.author;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.repositories.author.AuthorJpaRepository;
import com.example.demo.repositories.book.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorJpaRepository authorRepository;

    @Override
    public Author create(Author author) {
        author.setAuthorName(author.getAuthorName().toLowerCase());
        return authorRepository.save(author);
    }

    @Override
    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author readByAuthorName(String authorName) {
        return authorRepository.findByAuthorName(authorName.toLowerCase());
    }
}
