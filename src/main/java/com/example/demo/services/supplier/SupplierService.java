package com.example.demo.services.supplier;

import com.example.demo.models.Book;
import com.example.demo.models.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier create(Supplier supplier);

    List<Supplier> readAll();


    Supplier readBySupplierName(String supplierName);
}
