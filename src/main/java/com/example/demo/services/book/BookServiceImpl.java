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
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> readAll() {
        return bookRepository.findAll();
    }
}
