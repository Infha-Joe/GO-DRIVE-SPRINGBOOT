package com.example.vehiclerental.service;

import com.example.vehiclerental.dto.BookingRequest;
import com.example.vehiclerental.exception.CustomException;
import com.example.vehiclerental.model.Booking;
import com.example.vehiclerental.model.Customer;
import com.example.vehiclerental.model.Vehicle;
import com.example.vehiclerental.repository.BookingRepository;
import com.example.vehiclerental.repository.CustomerRepository;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Booking createBooking(BookingRequest req) {
        if (req.getDays() < 1 || req.getDays() > 365) {
            throw new CustomException("Days must be between 1 and 365");
        }

        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new CustomException("Customer not found"));

        Vehicle vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new CustomException("Vehicle not found"));

        if (!vehicle.isAvailable()) {
            throw new CustomException("Vehicle is not available");
        }

        vehicle.setAvailable(false);
        vehicleRepository.save(vehicle);

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setVehicle(vehicle);
        booking.setDays(req.getDays());
        booking.setBookingDate(LocalDate.now());
        booking.setTotalAmount(req.getDays() * vehicle.getRentPerDay());
        booking.setStatus("ACTIVE");

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getCustomerBookings(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException("Customer not found"));

        return bookingRepository.findByCustomer(customer);
    }

    public Booking requestCancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Booking not found"));

        if (!booking.getStatus().equals("ACTIVE")) {
            throw new CustomException("Only active bookings can request cancellation");
        }

        booking.setStatus("CANCEL_REQUESTED");
        return bookingRepository.save(booking);
    }

    public Booking approveCancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Booking not found"));

        if (!booking.getStatus().equals("CANCEL_REQUESTED")) {
            throw new CustomException("Only requested cancellations can be approved");
        }

        booking.setStatus("CANCELLED");
        booking.getVehicle().setAvailable(true);
        vehicleRepository.save(booking.getVehicle());

        return bookingRepository.save(booking);
    }

    public Booking completeBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Booking not found"));

        if (!booking.getStatus().equals("ACTIVE")) {
            throw new CustomException("Only active bookings can be completed");
        }

        booking.setStatus("COMPLETED");
        booking.getVehicle().setAvailable(true);
        vehicleRepository.save(booking.getVehicle());

        return bookingRepository.save(booking);
    }
}