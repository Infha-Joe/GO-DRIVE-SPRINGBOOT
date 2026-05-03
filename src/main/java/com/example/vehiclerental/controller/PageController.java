package com.example.vehiclerental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/admin-login")
    public String adminLogin() { return "adminLogin"; }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() { return "adminDashboard"; }

    @GetMapping("/add-vehicle")
    public String addVehicle() { return "addVehicle"; }

    @GetMapping("/view-vehicles")
    public String viewVehicles() { return "viewVehicles"; }

    @GetMapping("/view-bookings")
    public String viewBookings() { return "viewBookings"; }

    @GetMapping("/customer-login")
    public String customerLogin() { return "customerLogin"; }

    @GetMapping("/customer-register")
    public String customerRegister() { return "customerRegister"; }

    @GetMapping("/customer-dashboard")
    public String customerDashboard() { return "customerDashboard"; }

    @GetMapping("/available-vehicles")
    public String availableVehicles() { return "availableVehicles"; }

    @GetMapping("/book-vehicle")
    public String bookVehicle() { return "bookVehicle"; }

    @GetMapping("/my-bookings")
    public String myBookings() { return "myBookings"; }
}
