package com.api.erp.procurementManagement.repository;

import com.api.erp.procurementManagement.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {}

