package com.api.erp.inventoryManagement.entity;

import com.api.erp.inventoryManagement.entity.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="stock_movement")
public class Stock_Movements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;
}
