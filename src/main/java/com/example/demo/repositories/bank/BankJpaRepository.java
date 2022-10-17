package com.example.demo.repositories.bank;

import com.example.demo.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankJpaRepository extends JpaRepository<Bank, Long> {

}