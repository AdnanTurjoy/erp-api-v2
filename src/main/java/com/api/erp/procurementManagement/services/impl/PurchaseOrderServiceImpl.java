package com.api.erp.procurementManagement.services.impl;

import com.api.erp.procurementManagement.dtos.OrderStatus;
import com.api.erp.procurementManagement.entity.PurchaseOrder;
import com.api.erp.procurementManagement.repository.PurchaseOrderRepository;
import com.api.erp.procurementManagement.services.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;


    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder order) {
        return purchaseOrderRepository.save(order);
    }

    @Override
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder updatePurchaseOrderStatus(Long id, OrderStatus status) {
        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
        order.setStatus(status);
        return purchaseOrderRepository.save(order);
    }
}
