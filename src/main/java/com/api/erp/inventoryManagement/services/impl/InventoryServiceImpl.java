package com.api.erp.inventoryManagement.services.impl;

import com.api.erp.inventoryManagement.entity.Category;
import com.api.erp.inventoryManagement.entity.Product;
import com.api.erp.inventoryManagement.entity.Stock;
import com.api.erp.inventoryManagement.entity.StockMovement;
import com.api.erp.inventoryManagement.repository.CategoryRepository;
import com.api.erp.inventoryManagement.repository.ProductRepository;
import com.api.erp.inventoryManagement.repository.StockMovementRepository;
import com.api.erp.inventoryManagement.repository.StockRepository;
import com.api.erp.inventoryManagement.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockRepository stockRepository;
    private final StockMovementRepository stockMovementRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Stock updateStock(Long productId, int quantity, String location) {
        Stock stock = stockRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        stock.setQuantity(quantity);
        stock.setLocation(location);
        stock.setLastUpdated(LocalDateTime.now());
        return stockRepository.save(stock);
    }

    @Override
    public StockMovement recordStockMovement(Long productId, int quantity, String movementType) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        StockMovement movement = StockMovement.builder()
                .product(product)
                .quantity(quantity)
                .movementType(movementType)
                .timestamp(LocalDateTime.now())
                .build();
        return stockMovementRepository.save(movement);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

}
