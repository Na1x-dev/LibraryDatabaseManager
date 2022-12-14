package com.example.demo.repositories.book;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
    Book findBookByTitle(String title);
    Book findBookByTitleAndAuthorAuthorNameAndPublisherPublisherTitleAndLanguageLanguageName(String bookTitle, String authorName, String publisherTitle, String languageName);

    Book readByBookId(Long bookId);
}