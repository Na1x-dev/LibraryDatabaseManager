package com.example.demo.services.supply;

import com.example.demo.models.Book;
import com.example.demo.models.Supply;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.supply.SupplyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyJpaRepository supplyRepository;

    @Override
    public void create(Supply supply) {
        supplyRepository.save(supply);
    }

    @Override
    public List<Supply> readAll() {
        return supplyRepository.findAll();
    }
}
