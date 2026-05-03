package com.example.vehiclerental.repository;

import com.example.vehiclerental.model.Booking;
import com.example.vehiclerental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(Customer customer);
}