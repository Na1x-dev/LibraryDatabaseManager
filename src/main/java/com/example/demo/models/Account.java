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
@Data
@NoArgsConstructor
@Table(name = "accounts")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @NonNull
    @Column(name = "account_number")
    String accountNumber;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false, referencedColumnName = "bank_id")
    @NonNull
    Bank bank;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false, referencedColumnName = "currency_id")
    @NonNull
    Currency currency;

//    @JsonIgnore
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    @ToString.Exclude
//    List<Supplier> suppliers;
}
