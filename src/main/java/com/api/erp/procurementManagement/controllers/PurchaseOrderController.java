package com.api.erp.procurementManagement.controllers;

import com.api.erp.procurementManagement.dtos.OrderStatus;
import com.api.erp.procurementManagement.entity.PurchaseOrder;
import com.api.erp.procurementManagement.services.PurchaseOrderService;
import com.api.erp.utils.ApiResponse;
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
    public ApiResponse<PurchaseOrder> createOrder(@RequestBody PurchaseOrder order) {
        PurchaseOrder createdOrder = purchaseOrderService.createPurchaseOrder(order);
        return new ApiResponse<>(true, "Purchase order created successfully", createdOrder);
    }

    @GetMapping("/{id}")
    public ApiResponse<PurchaseOrder> getOrderById(@PathVariable("id") Long id) {
        return purchaseOrderService.getPurchaseOrderById(id)
                .map(order -> new ApiResponse<>(true, "Purchase order found", order))
                .orElse(new ApiResponse<>(false, "Purchase order not found", null));
    }

    @GetMapping
    public ApiResponse<List<PurchaseOrder>> getAllOrders() {
        List<PurchaseOrder> orders = purchaseOrderService.getAllPurchaseOrders();
        return new ApiResponse<>(true, "Purchase orders retrieved successfully", orders);
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<PurchaseOrder> updateOrderStatus(
            @PathVariable("id") Long id, @RequestParam OrderStatus status) {
        PurchaseOrder updatedOrder = purchaseOrderService.updatePurchaseOrderStatus(id, status);
        return new ApiResponse<>(true, "Purchase order status updated successfully", updatedOrder);
    }
}

