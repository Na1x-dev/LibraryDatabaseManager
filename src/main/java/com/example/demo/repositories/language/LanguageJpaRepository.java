package com.example.demo.repositories.language;

import com.example.demo.models.Book;
import com.example.demo.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageJpaRepository extends JpaRepository<Language, Long> {
}