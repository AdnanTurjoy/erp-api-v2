package com.api.erp.procurementManagement.services;

import com.api.erp.procurementManagement.dtos.OrderStatus;
import com.api.erp.procurementManagement.entity.PurchaseOrder;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {
    PurchaseOrder createPurchaseOrder(PurchaseOrder order);
    Optional<PurchaseOrder> getPurchaseOrderById(Long id);
    List<PurchaseOrder> getAllPurchaseOrders();
    PurchaseOrder updatePurchaseOrderStatus(Long id, OrderStatus status);
}
