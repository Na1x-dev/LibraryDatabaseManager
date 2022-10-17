package com.example.demo.services.sale;

import com.example.demo.models.Book;
import com.example.demo.models.Sale;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.sale.SaleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleJpaRepository saleRepository;

    @Override
    public void create(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public List<Sale> readAll() {
        return saleRepository.findAll();
    }
}
