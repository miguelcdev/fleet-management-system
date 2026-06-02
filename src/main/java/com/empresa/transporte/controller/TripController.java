package com.empresa.transporte.controller;

import com.empresa.transporte.model.Trip;
import com.empresa.transporte.service.TripService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/add")
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.saveTrip(trip);
    }

    @GetMapping("/list")
    public List<Trip> getTrips() {
        return tripService.getTrips();
    }

    @GetMapping(path = "/{id}")
    public Optional<Trip> getTrip(@PathVariable("id") Long id) {
        return tripService.getTrip(id);
    }

    @PutMapping(path = "/update/{id}")
    public Trip updateTrip(@RequestBody Trip trip, @PathVariable("id") Long id) {
        return tripService.updateTrip(trip, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteTrip(@PathVariable("id") Long id) {

        boolean tripDeleted = this.tripService.deleteTrip(id);

        if (tripDeleted) {
            return "The trip with id " + id + " has been deleted";
        } else {
            return "Error, we had a problem with the elimination of the trip with id " + id;
        }
    }
}