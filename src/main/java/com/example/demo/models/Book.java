package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    @NonNull
    @Column(name = "title")
    String title;

    @NonNull
    Author author;

    @NonNull
    Publisher publisher;

    @NonNull
    Genre genre;

    @NonNull
    @Column(name = "isbn")
    String isbn;


}
