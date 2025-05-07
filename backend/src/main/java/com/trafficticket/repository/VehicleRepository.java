package com.trafficticket.repository;

import com.trafficticket.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findByDriverLicenseNumber(String driverLicenseNumber);
} 