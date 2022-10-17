package com.example.demo.repositories.currency;

import com.example.demo.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyJpaRepository extends JpaRepository<Currency, Long> {
}