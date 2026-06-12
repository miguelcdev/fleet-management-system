package com.empresa.transporte.service;

import com.empresa.transporte.dto.VehicleRequestDTO;
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
    public Vehicle saveVehicle(VehicleRequestDTO vehicleDTO){

        //Validation of the License plate did not the same of the other vehicle registered
        if(vehicleRepository.existsByLicensePlate(vehicleDTO.getLicensePlate())){
            throw new RuntimeException("License plate already exists");
        }

        //Creation of new vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setLoadCapacityKg(vehicleDTO.getLoadCapacityKg());
        vehicle.setCompletedTrips(0);
        vehicle.setKilometersTraveled(0.0);

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
    public Vehicle updateById(VehicleRequestDTO request, Long id){

        //Search vehicle by id
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setLoadCapacityKg(request.getLoadCapacityKg());

        return vehicleRepository.save(vehicle);
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
