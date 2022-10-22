package com.example.demo.services.publisher;

import com.example.demo.models.Book;
import com.example.demo.models.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher create(Publisher publisher);

    List<Publisher> readAll();

    Publisher readByPublisherTitle(String publisherTitle);


}
