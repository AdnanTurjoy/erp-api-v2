package com.api.erp.procurementManagement.entity;

import com.api.erp.procurementManagement.dtos.ProcurementState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProcurementStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @Enumerated(EnumType.STRING)
    private ProcurementState status; // Enum: Ordered, Shipped, etc.

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
