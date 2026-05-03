package com.example.vehiclerental.model;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;   // CAR or BIKE
    private String model;
    private double rentPerDay;
    private boolean available = true;

    public Vehicle() {}

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getModel() { return model; }
    public double getRentPerDay() { return rentPerDay; }
    public boolean isAvailable() { return available; }
    public void setId(Long id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setModel(String model) { this.model = model; }
    public void setRentPerDay(double rentPerDay) { this.rentPerDay = rentPerDay; }
    public void setAvailable(boolean available) { this.available = available; }
}
