package com.example.demo.services.sale;

import com.example.demo.models.Sale;
import com.example.demo.repositories.sale.SaleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleJpaRepository saleRepository;

    @Override
    public Sale create(Sale sale) {
        saleRepository.save(sale);
        return sale;
    }

    @Override
    public List<Sale> readAll() {
        return saleRepository.findAll();
    }
}
