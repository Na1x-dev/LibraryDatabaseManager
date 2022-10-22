package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Sale> sales;
    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<SupplyDetail> supplyDetails;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false, referencedColumnName = "language_id")
    @NonNull
    Language language;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="isbn_id", referencedColumnName = "isbn_id")
    Isbn isbn;

    public Book(){
        title = "";
        author = new Author();
        publisher = new Publisher();
        genre = new Genre();
        language = new Language();
        isbn = new Isbn();

    }

    public Integer getBooksAmount() {
        Integer amount = 0;
//        System.out.println(supplyDetails);
        for (SupplyDetail supplyDetail : supplyDetails) {
            amount += supplyDetail.getAmount();
        }
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return bookId != null && Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author=" + author.getAuthorId() +
                ", publisher=" + publisher.getPublisherId() +
                ", genre=" + genre.getGenreId() +
                ", language=" + language.getLanguageId() +
                ", isbn=" + isbn.getIsbnId() +
                '}';
    }
}
