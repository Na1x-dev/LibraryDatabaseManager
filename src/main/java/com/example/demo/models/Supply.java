package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "supplies")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Supply {
    @Id
    @Column(name = "supply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "supply_date")
    @NonNull
    Date supplyDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "supplier_id")
    @NonNull
    Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "supply", orphanRemoval = true)
    @ToString.Exclude
    List<SupplyDetail> supplyDetails;

    public Supply() {
        supplyDate = new Date();
        supplier = new Supplier();
        supplyDetails = new ArrayList<>();
        supplyDetails.add(new SupplyDetail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supply supply = (Supply) o;
        return supplyId != null && Objects.equals(supplyId, supply.supplyId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Supply{" +
                "supplyId=" + supplyId +
                ", supplyDate=" + supplyDate +
                ", supplier=" + supplier.getSupplierId() +
                ", supplyDetails=" + supplyDetails +
                '}';
    }
}
