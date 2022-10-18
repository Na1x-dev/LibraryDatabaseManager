package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "languages")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long isbnId;

    @Column(name="language_name")
    @NonNull
    String languageName;

    @ManyToMany
    @NonNull
    @ToString.Exclude
    Set<Book> books;

}
