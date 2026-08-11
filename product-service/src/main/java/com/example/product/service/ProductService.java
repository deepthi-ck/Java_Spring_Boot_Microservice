package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
    }

    public Product create(ProductRequest request) {
        if (repository.existsBySkuIgnoreCase(request.getSku())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "SKU already exists");
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return repository.save(product);
    }

    public Product update(Long id, ProductRequest request) {
        Product existing = findById(id);
        existing.setName(request.getName());
        existing.setSku(request.getSku());
        existing.setPrice(request.getPrice());
        existing.setStock(request.getStock());
        return repository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}