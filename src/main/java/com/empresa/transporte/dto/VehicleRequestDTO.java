package com.empresa.transporte.dto;

public class VehicleRequestDTO {

    private String brand;
    private String model;
    private String licensePlate;
    private Double loadCapacityKg;

    public VehicleRequestDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getLoadCapacityKg() {
        return loadCapacityKg;
    }

    public void setLoadCapacityKg(Double loadCapacityKg) {
        this.loadCapacityKg = loadCapacityKg;
    }
}
