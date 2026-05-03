package com.example.vehiclerental.repository;

import com.example.vehiclerental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByAvailableTrue();
    List<Vehicle> findByModelContainingIgnoreCaseOrTypeContainingIgnoreCase(String model, String type);
}
