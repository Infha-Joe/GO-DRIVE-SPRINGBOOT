package com.example.vehiclerental.controller;

import com.example.vehiclerental.dto.LoginRequest;
import com.example.vehiclerental.dto.RegisterRequest;
import com.example.vehiclerental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest req) {
        return customerService.register(req);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        return customerService.login(request);
    }
}