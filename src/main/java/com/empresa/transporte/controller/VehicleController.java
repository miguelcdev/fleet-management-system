package com.empresa.transporte.controller;

import com.empresa.transporte.model.Vehicle;
import com.empresa.transporte.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.saveVehicle(vehicle);
    }

    @GetMapping("/list")
    public List<Vehicle> getVehicles(){
        return this.vehicleService.getVehicles();
    }

    @GetMapping(path = "/{id}")
    public Optional<Vehicle> getVehicle(@PathVariable("id") Long id){
        return vehicleService.getVehicle(id);
    }

    @PutMapping(path = "/{id}")
    public Vehicle updateById(@RequestBody Vehicle vehicle, @PathVariable("id") Long id){
        return vehicleService.updateById(vehicle,id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){

        boolean vehicleDeleted = this.vehicleService.deleteById(id);

        if (vehicleDeleted){
            return "The vehicle with id "+id+" has been deleted";
        }else{
           return "Error, we had a problem with the elimination of vehicle with id "+ id;
        }
    }
}
