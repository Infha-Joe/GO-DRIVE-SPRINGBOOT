package com.example.vehiclerental.controller;

import com.example.vehiclerental.dto.BookingRequest;
import com.example.vehiclerental.model.Booking;
import com.example.vehiclerental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/customer/{customerId}")
    public List<Booking> getCustomerBookings(@PathVariable Long customerId) {
        return bookingService.getCustomerBookings(customerId);
    }

    @PutMapping("/{id}/request-cancel")
    public Booking requestCancel(@PathVariable Long id) {
        return bookingService.requestCancel(id);
    }

    @PutMapping("/{id}/approve-cancel")
    public Booking approveCancel(@PathVariable Long id) {
        return bookingService.approveCancel(id);
    }

    @PutMapping("/{id}/complete")
    public Booking completeBooking(@PathVariable Long id) {
        return bookingService.completeBooking(id);
    }
}