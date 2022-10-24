package com.example.demo.services.sale;

import com.example.demo.models.Sale;

import java.util.List;

public interface SaleService {
    Sale create(Sale sale);

    List<Sale> readAll();

}
