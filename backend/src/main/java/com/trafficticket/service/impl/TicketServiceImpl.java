package com.trafficticket.service.impl;

import com.trafficticket.model.Ticket;
import com.trafficticket.repository.TicketRepository;
import com.trafficticket.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    
    private final TicketRepository ticketRepository;
    
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    
    @Override
    public List<Ticket> findByLicensePlate(String licensePlate) {
        return ticketRepository.findByLicensePlateNumber(licensePlate);
    }
    
    @Override
    public List<Ticket> findByDriverLicense(String driverLicense) {
        return ticketRepository.findByVehicle_DriverLicenseNumber(driverLicense);
    }
    
    @Override
    public Optional<Ticket> findByTicketNumber(String ticketNumber) {
        return ticketRepository.findById(ticketNumber);
    }
    
    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    @Override
    @Transactional
    public void markAsPaid(String ticketNumber) {
        ticketRepository.findById(ticketNumber).ifPresent(ticket -> {
            if (!ticket.isPaid()) {
                ticket.setPaid(true);
                ticketRepository.save(ticket);
            }
        });
    }
} 