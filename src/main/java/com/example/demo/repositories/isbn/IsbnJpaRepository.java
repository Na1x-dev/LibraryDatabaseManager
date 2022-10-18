package com.example.demo.repositories.isbn;

import com.example.demo.models.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsbnJpaRepository extends JpaRepository<Isbn, Long> {
}