package com.example.demo.services.supply;

import com.example.demo.models.Book;
import com.example.demo.models.Supply;

import java.util.List;

public interface SupplyService {
    Supply create(Supply supply);

    List<Supply> readAll();

}
