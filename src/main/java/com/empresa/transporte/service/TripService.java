package com.empresa.transporte.service;

import com.empresa.transporte.model.Trip;
import com.empresa.transporte.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip){
        return tripRepository.save(trip);
    }

    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getTrip(Long id){
        return tripRepository.findById(id);
    }

    public Trip updateTrip(Trip request, Long id){

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

    public boolean deleteTrip(Long id){
        try{
            tripRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
