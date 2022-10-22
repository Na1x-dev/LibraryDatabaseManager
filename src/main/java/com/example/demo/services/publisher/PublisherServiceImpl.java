package com.example.demo.services.publisher;

import com.example.demo.models.Book;
import com.example.demo.models.Publisher;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.publisher.PublisherJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherJpaRepository publisherJpaRepository;

    @Override
    public Publisher create(Publisher publisher) {
        publisher.setPublisherTitle(publisher.getPublisherTitle().toLowerCase());
        return publisherJpaRepository.save(publisher);
    }

    @Override
    public List<Publisher> readAll() {
        return publisherJpaRepository.findAll();
    }

    @Override
    public Publisher readByPublisherTitle(String publisherTitle) {
        return publisherJpaRepository.findByPublisherTitle(publisherTitle.toLowerCase());
    }
}
