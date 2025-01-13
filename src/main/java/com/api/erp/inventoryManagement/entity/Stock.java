package com.api.erp.inventoryManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;

    private int quantity;

    private String location;

    private Timestamp lastUpdatedAt;
}
