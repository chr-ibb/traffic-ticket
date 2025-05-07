package com.trafficticket.config;

import com.trafficticket.model.Ticket;
import com.trafficticket.model.Vehicle;
import com.trafficticket.service.TicketService;
import com.trafficticket.service.VehicleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Configuration
public class DataInitializer {

    private final Random random = new Random();

    @Bean
    public CommandLineRunner initData(VehicleService vehicleService, TicketService ticketService) {
        return args -> {
            // Create sample vehicles
            Vehicle vehicle1 = createVehicle("ABC1234", "DL123456", "Smith", "John");
            Vehicle vehicle2 = createVehicle("XYZ7890", "DL789012", "Johnson", "Jane");
            Vehicle vehicle3 = createVehicle("DEF5678", "DL345678", "Williams", "Bob");

            vehicleService.save(vehicle1);
            vehicleService.save(vehicle2);
            vehicleService.save(vehicle3);

            // Create sample tickets
            createAndSaveTicket(ticketService, "1000000001", Ticket.TicketType.SPEEDING, "ABC1234", new BigDecimal("150.00"), LocalDate.now().plusDays(30));
            createAndSaveTicket(ticketService, "1000000002", Ticket.TicketType.PARKING, "ABC1234", new BigDecimal("50.00"), LocalDate.now().plusDays(15));
            createAndSaveTicket(ticketService, "1000000003", Ticket.TicketType.SPEEDING, "XYZ7890", new BigDecimal("200.00"), LocalDate.now().plusDays(45));
            createAndSaveTicket(ticketService, "1000000004", Ticket.TicketType.PARKING, "DEF5678", new BigDecimal("75.00"), LocalDate.now().plusDays(20));
            
            // Create a paid ticket
            Ticket paidTicket = createTicket("1000000005", Ticket.TicketType.PARKING, "DEF5678", new BigDecimal("100.00"), LocalDate.now().plusDays(10));
            paidTicket.setPaid(true);
            ticketService.save(paidTicket);
        };
    }

    private Vehicle createVehicle(String licensePlate, String driverLicense, String lastName, String firstName) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlateNumber(licensePlate);
        vehicle.setDriverLicenseNumber(driverLicense);
        vehicle.setOwnerLastName(lastName);
        vehicle.setOwnerFirstName(firstName);
        return vehicle;
    }

    private void createAndSaveTicket(TicketService ticketService, String ticketNumber, Ticket.TicketType type, 
                                   String licensePlate, BigDecimal amount, LocalDate dueDate) {
        Ticket ticket = createTicket(ticketNumber, type, licensePlate, amount, dueDate);
        ticketService.save(ticket);
    }

    private Ticket createTicket(String ticketNumber, Ticket.TicketType type, String licensePlate, 
                              BigDecimal amount, LocalDate dueDate) {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setTicketType(type);
        ticket.setLicensePlateNumber(licensePlate);
        ticket.setFineAmount(amount);
        ticket.setDueDate(dueDate);
        ticket.setPaid(false);
        return ticket;
    }
} 