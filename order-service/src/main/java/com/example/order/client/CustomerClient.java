package com.example.order.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CustomerClient {
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomer(Long customerId) {
        return restTemplate.getForObject("http://customer-service/customers/{id}", Map.class, customerId);
    }
}