package com.example.customer.service;

import com.example.customer.dto.CustomerRequest;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id));
    }

    public Customer create(CustomerRequest request) {
        if (repository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        Customer customer = new Customer();
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setCity(request.getCity());
        return repository.save(customer);
    }

    public Customer update(Long id, CustomerRequest request) {
        Customer existing = findById(id);
        existing.setFullName(request.getFullName());
        existing.setEmail(request.getEmail());
        existing.setCity(request.getCity());
        return repository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}