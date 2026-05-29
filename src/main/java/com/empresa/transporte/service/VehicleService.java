package com.empresa.transporte.service;

import com.empresa.transporte.model.Vehicle;
import com.empresa.transporte.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    //Injection of dependencies by constructor
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    //Create a new vehicle
    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    //List vehicles
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    //Search vehicle information by id
    public Optional<Vehicle> getVehicle(Long id){
        return  vehicleRepository.findById(id);
    }

    //Update vehicle
    public Vehicle updateById(Vehicle request, Long id){

        //Search vehicle by id
        Optional<Vehicle> findVehicle = vehicleRepository.findById(id);

        if (findVehicle.isPresent()){
            Vehicle vehicle = findVehicle.get();

            vehicle.setBrand(request.getBrand());
            vehicle.setModel(request.getModel());
            vehicle.setLicensePlate(request.getLicensePlate());
            vehicle.setLoadCapacityKg(request.getLoadCapacityKg());
            vehicle.setCompletedTrips(request.getCompletedTrips());
            vehicle.setKilometersTraveled(request.getKilometersTraveled());

            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    //Delete vehicle by id
    public Boolean deleteById(Long id){
        try{
            vehicleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
