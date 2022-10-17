package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "books")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    @NonNull
    @Column(name = "title")
    String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "author_id")
    @NonNull
    Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false, referencedColumnName = "publisher_id")
    @NonNull
    Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false, referencedColumnName = "genre_id")
    @NonNull
    Genre genre;

    @NonNull
    @Column(name = "isbn")
    String isbn;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Sale> sales;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<SupplyDetail> supplyDetails;

    public Integer getBooksAmount() {
        Integer amount = 0;
//        System.out.println(supplyDetails);
        for (SupplyDetail supplyDetail : supplyDetails) {
            amount += supplyDetail.getAmount();
        }
        return amount;
    }

}
