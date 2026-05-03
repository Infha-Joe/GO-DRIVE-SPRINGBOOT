package com.example.vehiclerental.dto;

public class BookingRequest {
    private Long customerId;
    private Long vehicleId;
    private int days;

    public BookingRequest() {}
    public Long getCustomerId() { return customerId; }
    public Long getVehicleId() { return vehicleId; }
    public int getDays() { return days; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
    public void setDays(int days) { this.days = days; }
}
