package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @NonNull
    @Column(name = "account_number")
    String accountNumber;

    @NonNull
    Bank bank;

    @NonNull
    Currency currency;

}
