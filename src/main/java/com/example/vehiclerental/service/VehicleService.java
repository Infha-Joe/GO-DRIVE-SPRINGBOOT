package com.example.vehiclerental.service;

import com.example.vehiclerental.exception.CustomException;
import com.example.vehiclerental.model.Vehicle;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicle.getModel() == null || vehicle.getModel().isBlank()) {
            throw new CustomException("Model name is required");
        }
        if (vehicle.getRentPerDay() < 100) {
            throw new CustomException("Rent per day must be at least ₹100");
        }
        vehicle.setAvailable(true);
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailableTrue();
    }

    public Vehicle updateVehicle(Long id, Vehicle updated) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Vehicle not found"));
        if (updated.getRentPerDay() < 100) {
            throw new CustomException("Rent per day must be at least ₹100");
        }
        vehicle.setType(updated.getType());
        vehicle.setModel(updated.getModel());
        vehicle.setRentPerDay(updated.getRentPerDay());
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new CustomException("Vehicle not found");
        }
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> searchVehicles(String query) {
        return vehicleRepository.findByModelContainingIgnoreCaseOrTypeContainingIgnoreCase(query, query);
    }
}
