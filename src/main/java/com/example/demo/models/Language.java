package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
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
    Long languageId;

    @Column(name="language_name")
    @NonNull
    String languageName;

    @JsonIgnore
    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Book> books;

}
