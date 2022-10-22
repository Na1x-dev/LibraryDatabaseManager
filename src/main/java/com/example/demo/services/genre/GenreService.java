package com.example.demo.services.genre;

import com.example.demo.models.Book;
import com.example.demo.models.Genre;

import java.util.List;

public interface GenreService {
    Genre create(Genre genre);

    List<Genre> readAll();


    Genre readByGenreName(String genreName);
}
