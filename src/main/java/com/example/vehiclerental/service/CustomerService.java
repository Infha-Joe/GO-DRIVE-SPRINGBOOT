package com.example.vehiclerental.service;

import com.example.vehiclerental.dto.LoginRequest;
import com.example.vehiclerental.dto.RegisterRequest;
import com.example.vehiclerental.exception.CustomException;
import com.example.vehiclerental.model.Customer;
import com.example.vehiclerental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Map<String, String> register(RegisterRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new CustomException("Name is required");
        }
        if (req.getEmail() == null || req.getEmail().isBlank()) {
            throw new CustomException("Email is required");
        }
        if (req.getPassword() == null || req.getPassword().length() < 4) {
            throw new CustomException("Password must be at least 4 characters");
        }
        if (customerRepository.existsByEmail(req.getEmail())) {
            throw new CustomException("Email already registered");
        }

        Customer customer = new Customer();
        customer.setName(req.getName().trim());
        customer.setEmail(req.getEmail().trim().toLowerCase());
        customer.setPassword(req.getPassword());
        customerRepository.save(customer);

        return Map.of("message", "Registration successful");
    }

    public Map<String, Object> login(LoginRequest req) {
        Customer customer = customerRepository.findByEmail(req.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new CustomException("Invalid email or password"));

        if (!customer.getPassword().equals(req.getPassword())) {
            throw new CustomException("Invalid email or password");
        }

        return Map.of(
                "message", "Login successful",
                "customerId", customer.getId(),
                "name", customer.getName(),
                "email", customer.getEmail(),
                "role", "CUSTOMER"
        );
    }
}
