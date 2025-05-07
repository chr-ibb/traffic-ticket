package com.trafficticket.controller;

import com.trafficticket.dto.TicketSearchRequest;
import com.trafficticket.model.Ticket;
import com.trafficticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200") // Angular default port
public class TicketController {
    
    private final TicketService ticketService;
    
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    @PostMapping("/search")
    public ResponseEntity<List<Ticket>> searchTickets(@Valid @RequestBody TicketSearchRequest request) {
        List<Ticket> tickets;
        if (request.getLicensePlate() != null && !request.getLicensePlate().isEmpty()) {
            tickets = ticketService.findByLicensePlate(request.getLicensePlate());
        } else {
            tickets = ticketService.findByDriverLicense(request.getDriverLicense());
        }
        return ResponseEntity.ok(tickets);
    }
    
    @GetMapping("/{ticketNumber}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String ticketNumber) {
        return ticketService.findByTicketNumber(ticketNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{ticketNumber}/pay")
    public ResponseEntity<?> payTicket(@PathVariable String ticketNumber) {
        return ticketService.findByTicketNumber(ticketNumber)
                .map(ticket -> {
                    if (ticket.isPaid()) {
                        return ResponseEntity.badRequest()
                                .body(Map.of("message", "Ticket is already paid"));
                    }
                    ticketService.markAsPaid(ticketNumber);
                    return ResponseEntity.ok(Map.of(
                            "message", "Ticket paid successfully",
                            "ticketNumber", ticketNumber
                    ));
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 