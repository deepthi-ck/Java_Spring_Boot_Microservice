package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService(new ProductRepository());
    }

    @Test
    void createAndFind() {
        ProductRequest req = new ProductRequest();
        req.setName("Book");
        req.setSku("BOOK-1");
        req.setPrice(12.5);
        req.setStock(5);
        Product created = service.create(req);
        assertNotNull(created.getId());
        assertEquals("BOOK-1", service.findById(created.getId()).getSku());
    }
}