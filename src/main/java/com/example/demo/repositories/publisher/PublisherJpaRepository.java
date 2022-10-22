package com.example.demo.repositories.publisher;

import com.example.demo.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherJpaRepository extends JpaRepository<Publisher, Long> {
    Publisher findByPublisherTitle(String publisherTitle);

}