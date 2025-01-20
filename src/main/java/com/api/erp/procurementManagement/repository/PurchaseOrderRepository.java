package com.api.erp.procurementManagement.repository;

import com.api.erp.procurementManagement.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {}

