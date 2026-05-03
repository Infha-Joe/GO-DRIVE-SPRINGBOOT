package com.example.vehiclerental.service;

import com.example.vehiclerental.dto.AdminLoginRequest;
import com.example.vehiclerental.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminService {

    public Map<String, Object> login(AdminLoginRequest req) {
        String email = req.getEmail().trim();
        String password = req.getPassword();

        if ("admin@gmail.com".equals(email) && "admin123".equals(password)) {
            return Map.of(
                    "message", "Login successful",
                    "email", email,
                    "role", "ADMIN"
            );
        }

        throw new CustomException("Invalid email or password");
    }
}