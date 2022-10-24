package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "sales")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Sale {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long saleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "sale_date")
    @NonNull
    Date saleDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "book_id")
    @NonNull
    Book book;

    @NonNull
    @Column(name = "price")
    Double price;

    @Column(name = "amount")
    @NonNull
    Long amount;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "client_id")
    @NonNull
    Client client;

    public Sale() {
        client = new Client();
        book = new Book();
        amount = 1L;
        price = 0D;
        saleDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return saleId != null && Objects.equals(saleId, sale.saleId);
    }
    public String getDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        return format.format(saleDate);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
