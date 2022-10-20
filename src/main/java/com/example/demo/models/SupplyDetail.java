package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "supply_details")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class SupplyDetail {
    @Id
    @Column(name = "supply_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyDetailId;

    @ManyToOne
    @JoinColumn(name = "supply_id", nullable = false, referencedColumnName = "supply_id")
    @NonNull
    Supply supply;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "book_id")
    @NonNull
    Book book;
    @NonNull
    Integer amount;

    public SupplyDetail() {
//        supply = new Supply();
        book = new Book();
        amount = 1;
    }
}
