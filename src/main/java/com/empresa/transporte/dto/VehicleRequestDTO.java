package com.empresa.transporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class VehicleRequestDTO {

    @NotBlank(message = "The brand can't be void")
    private String brand;

    @NotBlank
    private String model;

    @NotBlank(message = "The license plate can´t be void")
    private String licensePlate;

    @NotBlank
    @Positive(message = "The load capacity should be writing in positive number")
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
