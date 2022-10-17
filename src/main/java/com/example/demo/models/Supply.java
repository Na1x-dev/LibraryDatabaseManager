package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "supplies")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Supply {
    @Id
    @Column(name = "supply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyId;

    @Column(name = "supply_date")
    @NonNull
    Date supplyDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "supplier_id")
    @NonNull
    Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<SupplyDetail> supplyDetails;
}
