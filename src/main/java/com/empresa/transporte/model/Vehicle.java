package com.empresa.transporte.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    private Double loadCapacityKg;
    private Integer completedTrips;
    private Double kilometersTraveled;

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, String model, String licensePlate, Double loadCapacityKg, Integer completedTrips, Double kilometersTraveled) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.loadCapacityKg = loadCapacityKg;
        this.completedTrips = completedTrips;
        this.kilometersTraveled = kilometersTraveled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCompletedTrips() {
        return completedTrips;
    }

    public void setCompletedTrips(Integer completedTrips) {
        this.completedTrips = completedTrips;
    }

    public Double getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(Double kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }
}
