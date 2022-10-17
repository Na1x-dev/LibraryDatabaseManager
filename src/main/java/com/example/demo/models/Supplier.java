package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "suppliers")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplierId;

    @Column(name = "supplier_name")
    @NonNull
    String supplierName;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Supply> supplies;

//    @ManyToOne
//    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "account_id")
//    @NonNull
//    Account account;
}