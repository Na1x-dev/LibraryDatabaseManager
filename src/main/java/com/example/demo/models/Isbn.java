package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "isbn_table")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Isbn {
    @Id
    @Column(name = "isbn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long isbnId;

    @Column(name = "isbn_number")
    @NonNull
    String isbnNumber;

    @OneToOne(mappedBy = "isbn")
    @NonNull
    Book book;

    public Isbn() {
        isbnNumber = "";
//        book = new Book();
    }

    public void generateIsbnNumber(Book book) {
        isbnNumber = "978-3-" + stringToAscii(book.title, 5) + "-" + stringToAscii(book.author.getAuthorName(), 3);
    }
    private String stringToAscii(String str, int n) {
        char[] strCharArray = str.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        if (strCharArray.length < n) {
            n = strCharArray.length;
        }
        for (int i = 0; i < n; i++) {
            strBuilder.append((int)strCharArray[i]);
        }

        return strBuilder.toString();
    }
}
