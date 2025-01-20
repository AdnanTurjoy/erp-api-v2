package com.api.erp.inventoryManagement.controllers;

import com.api.erp.inventoryManagement.entity.Category;
import com.api.erp.inventoryManagement.entity.Product;
import com.api.erp.inventoryManagement.entity.Stock;
import com.api.erp.inventoryManagement.entity.StockMovement;
import com.api.erp.inventoryManagement.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return inventoryService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return inventoryService.saveProduct(product);
    }

    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        return inventoryService.saveCategory(category);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return inventoryService.getAllCategories();
    }

    @PostMapping("/stock")
    public Stock createStock(@RequestBody Stock stock) {
        return inventoryService.saveStock(stock);
    }

    @PutMapping("/stock/{productId}")
    public Stock updateStock(@PathVariable Long productId, @RequestParam int quantity, @RequestParam String location) {
        return inventoryService.updateStock(productId, quantity, location);
    }

    @PostMapping("/stock-movements")
    public StockMovement recordStockMovement(@RequestParam Long productId, @RequestParam int quantity, @RequestParam String movementType) {
        return inventoryService.recordStockMovement(productId, quantity, movementType);
    }
}
