package com.trafficticket.service.impl;

import com.trafficticket.model.Vehicle;
import com.trafficticket.repository.VehicleRepository;
import com.trafficticket.service.VehicleService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    
    private final VehicleRepository vehicleRepository;
    
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    @Override
    public Optional<Vehicle> findByLicensePlate(String licensePlate) {
        return vehicleRepository.findById(licensePlate);
    }
    
    @Override
    public Optional<Vehicle> findByDriverLicense(String driverLicense) {
        return vehicleRepository.findByDriverLicenseNumber(driverLicense);
    }
    
    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
} 