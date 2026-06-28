package com.empresa.transporte.service;

import com.empresa.transporte.dto.TripRequestDTO;
import com.empresa.transporte.model.Trip;
import com.empresa.transporte.model.Vehicle;
import com.empresa.transporte.repository.TripRepository;
import com.empresa.transporte.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Trip saveTrip(TripRequestDTO tripDTO){

        Vehicle vehicle = validateVehicleId(tripDTO.getVehicleId());

        vehicle.setCompletedTrips(vehicle.getCompletedTrips()+1);
        vehicle.setKilometersTraveled(vehicle.getKilometersTraveled()+ tripDTO.getDistanceKm());

        Trip trip = new Trip();
        trip.setOrigin(tripDTO.getOrigin());
        trip.setDestination(tripDTO.getDestination());
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setArrivalDate(tripDTO.getArrivalDate());
        trip.setDistanceKm(tripDTO.getDistanceKm());
        trip.setVehicle(vehicle);

        //Validation of date insert by user
        validateTripDates(tripDTO);
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
    public Trip updateTrip(TripRequestDTO request, Long id){
        //Search the trip by id
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));

        //Search a vehicle by id, which the user want to update for the trip selected by id
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found"));

        //Validation update of a date
        validateTripDates(request);

        trip.setOrigin(request.getOrigin());
        trip.setDestination(request.getDestination());
        trip.setDepartureDate(request.getDepartureDate());
        trip.setArrivalDate(request.getArrivalDate());
        trip.setDistanceKm(request.getDistanceKm());
        trip.setVehicle(vehicle);

        return tripRepository.save(trip);
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

    public void validateTripDates(TripRequestDTO tripDTO){
        //Validation of the arrival date of trip
        if (tripDTO.getArrivalDate().isBefore(tripDTO.getDepartureDate())){
            throw new RuntimeException("Arrival cannot be before departure day");
        }

        //Validation of departure date of trip
        if (tripDTO.getDepartureDate().isBefore(LocalDate.now())){
            throw  new RuntimeException("Departure day cannot be in the past");
        }
    }

    public Vehicle validateVehicleId(Long id){
        return  vehicleRepository.findById(id).orElseThrow(()-> new RuntimeException("Vehicle not found"));
    }
}
