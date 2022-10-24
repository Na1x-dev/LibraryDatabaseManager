package com.example.demo.services.language;

import com.example.demo.models.Book;
import com.example.demo.models.Language;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.language.LanguageJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageJpaRepository languageJpaRepository;

    @Override
    public Language create(Language language) {
        language.setLanguageName(language.getLanguageName().toLowerCase().trim());
        return languageJpaRepository.save(language);
    }

    @Override
    public List<Language> readAll() {
        return languageJpaRepository.findAll();
    }

    @Override
    public Language readByLanguageName(String languageName) {
        return languageJpaRepository.findByLanguageName(languageName.toLowerCase().trim());
    }
}
