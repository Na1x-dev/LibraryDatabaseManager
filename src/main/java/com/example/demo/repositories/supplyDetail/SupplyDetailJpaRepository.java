package com.example.demo.repositories.supplyDetail;

import com.example.demo.models.SupplyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyDetailJpaRepository extends JpaRepository<SupplyDetail, Long> {


}