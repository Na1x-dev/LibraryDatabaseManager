package com.example.demo.services.genre;

import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.genre.GenreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreJpaRepository genreRepository;

    @Override
    public Genre create(Genre genre) {
        genre.setGenreName(genre.getGenreName().toLowerCase().trim());
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> readAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre readByGenreName(String genreName) {
        return genreRepository.findByGenreName(genreName.toLowerCase().trim());
    }
}
