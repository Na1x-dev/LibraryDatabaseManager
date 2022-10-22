package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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
    @ManyToOne(cascade=CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SupplyDetail that = (SupplyDetail) o;
        return supplyDetailId != null && Objects.equals(supplyDetailId, that.supplyDetailId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "SupplyDetail{" +
                "supplyDetailId=" + supplyDetailId +

                ", book=" + book.getBookId() +
                ", amount=" + amount +
                '}';
    }
}
