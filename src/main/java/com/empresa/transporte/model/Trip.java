package com.empresa.transporte.model;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDate;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trip")
    private Long id;

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Double distanceKm;

    @ManyToOne(targetEntity = Vehicle.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;

}
