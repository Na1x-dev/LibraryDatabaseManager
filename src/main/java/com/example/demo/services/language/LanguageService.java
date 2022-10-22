package com.example.demo.services.language;

import com.example.demo.models.Book;
import com.example.demo.models.Language;

import java.util.List;

public interface LanguageService {
    Language create(Language language);

    List<Language> readAll();


    Language readByLanguageName(String languageName);
}
