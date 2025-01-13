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
@Entity(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    private String sku;

    private double price;

    private long categoryId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
