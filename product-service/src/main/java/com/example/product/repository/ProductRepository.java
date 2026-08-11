package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(seq.getAndIncrement());
        }
        store.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsBySkuIgnoreCase(String sku) {
        return store.values().stream().anyMatch(p -> p.getSku() != null && p.getSku().equalsIgnoreCase(sku));
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}