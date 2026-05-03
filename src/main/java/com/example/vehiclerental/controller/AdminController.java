package com.example.vehiclerental.controller;

import com.example.vehiclerental.dto.AdminLoginRequest;
import com.example.vehiclerental.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AdminLoginRequest request) {
        return adminService.login(request);
    }
}