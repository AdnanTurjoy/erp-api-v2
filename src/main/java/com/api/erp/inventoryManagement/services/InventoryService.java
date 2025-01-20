package com.api.erp.inventoryManagement.services;

import com.api.erp.inventoryManagement.entity.Category;
import com.api.erp.inventoryManagement.entity.Product;
import com.api.erp.inventoryManagement.entity.Stock;
import com.api.erp.inventoryManagement.entity.StockMovement;
import java.util.List;
import java.util.Optional;


public interface InventoryService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product saveProduct(Product product);
    List<Category> getAllCategories();
    Stock updateStock(Long productId, int quantity, String location);
    StockMovement recordStockMovement(Long productId, int quantity, String movementType);
    Category saveCategory(Category category);
    Stock saveStock(Stock stock);
}
