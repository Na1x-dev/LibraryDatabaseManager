package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "isbn_table")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Isbn {
    @Id
    @Column(name = "isbn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long isbnId;

    @Column(name="isbn_number")
    @NonNull
    String isbnNumber;

    @OneToOne(mappedBy = "isbn")
    @NonNull

    Book book;

}
