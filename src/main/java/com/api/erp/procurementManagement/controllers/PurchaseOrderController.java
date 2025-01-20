package com.api.erp.procurementManagement.controllers;

import com.api.erp.procurementManagement.dtos.OrderStatus;
import com.api.erp.procurementManagement.entity.PurchaseOrder;
import com.api.erp.procurementManagement.services.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;


    @PostMapping
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody PurchaseOrder order) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PurchaseOrder> updateOrderStatus(
            @PathVariable Long id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(purchaseOrderService.updatePurchaseOrderStatus(id, status));
    }
}

