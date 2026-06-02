package com.empresa.transporte.repository;

import com.empresa.transporte.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
