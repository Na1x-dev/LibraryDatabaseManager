package com.example.demo.services.supplyDetail;

import com.example.demo.models.Book;
import com.example.demo.models.SupplyDetail;

import java.util.List;

public interface SupplyDetailService {
    SupplyDetail create(SupplyDetail supplyDetail);

    List<SupplyDetail> readAll();



}
