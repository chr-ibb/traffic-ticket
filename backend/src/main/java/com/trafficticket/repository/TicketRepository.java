package com.trafficticket.repository;

import com.trafficticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByLicensePlateNumber(String licensePlateNumber);
    List<Ticket> findByVehicle_DriverLicenseNumber(String driverLicenseNumber);
} 