package com.api.erp.inventoryManagement.repository;

import com.api.erp.inventoryManagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
