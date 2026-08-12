package com.example.order.service;

import com.example.order.client.CustomerClient;
import com.example.order.client.ProductClient;
import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderRequest;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Test
    void createOrderUsesClients() {
        CustomerClient customerClient = mock(CustomerClient.class);
        ProductClient productClient = mock(ProductClient.class);
        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1L);
        Map<String, Object> product = new HashMap<>();
        product.put("id", 10L);
        product.put("price", 5.0);
        when(customerClient.getCustomer(1L)).thenReturn(customer);
        when(productClient.getProduct(10L)).thenReturn(product);

        OrderService service = new OrderService(new OrderRepository(), customerClient, productClient);
        OrderRequest req = new OrderRequest();
        req.setCustomerId(1L);
        OrderLineRequest line = new OrderLineRequest();
        line.setProductId(10L);
        line.setQuantity(2);
        req.setLines(Collections.singletonList(line));

        Order order = service.create(req);
        assertEquals("NEW", order.getStatus());
        assertEquals(10.0, order.getTotalAmount(), 0.0001);
    }
}