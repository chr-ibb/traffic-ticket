package com.trafficticket.service;

import com.trafficticket.model.Vehicle;
import java.util.Optional;

public interface VehicleService {
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    Optional<Vehicle> findByDriverLicense(String driverLicense);
    Vehicle save(Vehicle vehicle);
} 