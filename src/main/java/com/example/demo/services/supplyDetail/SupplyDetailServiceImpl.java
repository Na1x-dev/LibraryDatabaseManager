package com.example.demo.services.supplyDetail;

import com.example.demo.models.Book;
import com.example.demo.models.SupplyDetail;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.supplyDetail.SupplyDetailJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyDetailServiceImpl implements SupplyDetailService {

    @Autowired
    SupplyDetailJpaRepository supplyDetailJpaRepository;

    @Override
    public SupplyDetail create(SupplyDetail supplyDetail) {
        return supplyDetailJpaRepository.save(supplyDetail);
    }

    @Override
    public List<SupplyDetail> readAll() {
        return supplyDetailJpaRepository.findAll();
    }

    @Override
    public boolean update(Long id, SupplyDetail supplyDetail) {
        if (supplyDetailJpaRepository.existsById(id)) {
            supplyDetail.setSupplyDetailId(id);
            supplyDetailJpaRepository.save(supplyDetail);
            return true;
        }
        return false;
    }

}
