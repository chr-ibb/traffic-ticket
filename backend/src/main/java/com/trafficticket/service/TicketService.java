package com.trafficticket.service;

import com.trafficticket.model.Ticket;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> findByLicensePlate(String licensePlate);
    List<Ticket> findByDriverLicense(String driverLicense);
    Optional<Ticket> findByTicketNumber(String ticketNumber);
    Ticket save(Ticket ticket);
    void markAsPaid(String ticketNumber);
} 