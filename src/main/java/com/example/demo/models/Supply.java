package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
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
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<SupplyDetail> supplyDetails;

    public Supply() {
        supplyDate = new Date();
        supplier = new Supplier();
        supplyDetails = new ArrayList<>();
        supplyDetails.add(new SupplyDetail());
    }

}
