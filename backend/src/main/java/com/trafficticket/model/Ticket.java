package com.trafficticket.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    public enum TicketType {
        PARKING,
        SPEEDING
    }

    @Id
    private String ticketNumber;  // 10-digit number
    
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    
    private String licensePlateNumber;
    private BigDecimal fineAmount;
    private LocalDate dueDate;
    private boolean paid;
    
    @ManyToOne
    @JoinColumn(name = "licensePlateNumber", insertable = false, updatable = false)
    private Vehicle vehicle;
} 

