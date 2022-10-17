package com.example.demo.repositories.sale;

import com.example.demo.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleJpaRepository extends JpaRepository<Sale, Long> {
}