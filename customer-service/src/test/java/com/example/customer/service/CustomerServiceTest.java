package com.example.customer.service;

import com.example.customer.dto.CustomerRequest;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerService service;

    @BeforeEach
    void setUp() {
        service = new CustomerService(new CustomerRepository());
    }

    @Test
    void createAndFind() {
        CustomerRequest req = new CustomerRequest();
        req.setFullName("Ada");
        req.setEmail("ada@example.com");
        req.setCity("NYC");
        Customer created = service.create(req);
        assertNotNull(created.getId());
        assertEquals("Ada", service.findById(created.getId()).getFullName());
    }

    @Test
    void duplicateEmailThrows() {
        CustomerRequest req = new CustomerRequest();
        req.setFullName("Ada");
        req.setEmail("ada@example.com");
        service.create(req);
        CustomerRequest dup = new CustomerRequest();
        dup.setFullName("Bob");
        dup.setEmail("ada@example.com");
        assertThrows(ResponseStatusException.class, () -> service.create(dup));
    }
}