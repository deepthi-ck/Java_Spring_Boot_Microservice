package com.example.order.service;

import com.example.order.client.CustomerClient;
import com.example.order.client.ProductClient;
import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderRequest;
import com.example.order.model.Order;
import com.example.order.model.OrderLine;
import com.example.order.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public OrderService(OrderRepository repository, CustomerClient customerClient, ProductClient productClient) {
        this.repository = repository;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
    }

    public Order create(OrderRequest request) {
        Map<String, Object> customer = customerClient.getCustomer(request.getCustomerId());
        if (customer == null || customer.get("id") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
        }

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setStatus("NEW");
        List<OrderLine> lines = new ArrayList<>();
        double total = 0.0;
        for (OrderLineRequest lineReq : request.getLines()) {
            Map<String, Object> product = productClient.getProduct(lineReq.getProductId());
            if (product == null || product.get("id") == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found: " + lineReq.getProductId());
            }
            double unitPrice = ((Number) product.get("price")).doubleValue();
            OrderLine line = new OrderLine();
            line.setProductId(lineReq.getProductId());
            line.setQuantity(lineReq.getQuantity());
            line.setUnitPrice(unitPrice);
            lines.add(line);
            total += unitPrice * lineReq.getQuantity();
        }
        order.setLines(lines);
        order.setTotalAmount(total);
        return repository.save(order);
    }
}