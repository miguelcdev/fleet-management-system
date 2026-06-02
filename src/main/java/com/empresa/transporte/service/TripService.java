package com.empresa.transporte.service;

import com.empresa.transporte.model.Trip;
import com.empresa.transporte.model.Vehicle;
import com.empresa.transporte.repository.TripRepository;
import com.empresa.transporte.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    //Injection of dependencies by constructor
    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;

    public TripService(TripRepository tripRepository, VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    //Create new trip
    public Trip saveTrip(Trip trip){
        Long vehicleId = trip.getVehicle().getId();
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setCompletedTrips(vehicle.getCompletedTrips()+1);
        vehicle.setKilometersTraveled(vehicle.getKilometersTraveled()+ trip.getDistanceKm());

        trip.setVehicle(vehicle);
        vehicleRepository.save(vehicle);
        return tripRepository.save(trip);
    }

    //List trips
    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    //Search a trip by id
    public Optional<Trip> getTrip(Long id){
        return tripRepository.findById(id);
    }

    //Update trip
    public Trip updateTrip(Trip request, Long id){
        //Find trip by id
        Optional<Trip> findTrip = tripRepository.findById(id);

        if(findTrip.isPresent()){
            Trip trip = findTrip.get();
            trip.setOrigin(request.getOrigin());
            trip.setDestination(request.getDestination());
            trip.setDepartureDate(request.getDepartureDate());
            trip.setArrivalDate(request.getArrivalDate());
            trip.setDistanceKm(request.getDistanceKm());

            return tripRepository.save(trip);
        }
        return null;
    }

    //Delete trip by id
    public boolean deleteTrip(Long id){
        try{
            tripRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
