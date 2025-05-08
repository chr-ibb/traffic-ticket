package com.trafficticket.service;

import com.trafficticket.model.Ticket;
import com.trafficticket.model.Vehicle;
import com.trafficticket.repository.TicketRepository;
import com.trafficticket.repository.VehicleRepository;
import com.trafficticket.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Vehicle testVehicle;
    private Ticket testTicket;

    @BeforeEach
    void setUp() {
        // Setup test data
        testVehicle = new Vehicle();
        testVehicle.setLicensePlateNumber("ABC123");
        testVehicle.setDriverLicenseNumber("DL123456");
        testVehicle.setOwnerFirstName("John");
        testVehicle.setOwnerLastName("Doe");

        testTicket = new Ticket();
        testTicket.setTicketNumber("T123456789");
        testTicket.setTicketType(Ticket.TicketType.SPEEDING);
        testTicket.setLicensePlateNumber("ABC123");
        testTicket.setFineAmount(new BigDecimal("100.00"));
        testTicket.setDueDate(LocalDate.now().plusDays(30));
        testTicket.setPaid(false);
        testTicket.setVehicle(testVehicle);
    }

    @Test
    void findByLicensePlate_ShouldReturnMatchingTickets() {
        // Arrange
        String licensePlate = "ABC123";
        when(ticketRepository.findByLicensePlateNumber(licensePlate))
            .thenReturn(Arrays.asList(testTicket));

        // Act
        List<Ticket> result = ticketService.findByLicensePlate(licensePlate);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(licensePlate, result.get(0).getLicensePlateNumber());
        verify(ticketRepository).findByLicensePlateNumber(licensePlate);
    }

    @Test
    void findByDriverLicense_ShouldReturnMatchingTickets() {
        // Arrange
        String driverLicense = "DL123456";
        when(ticketRepository.findByVehicle_DriverLicenseNumber(driverLicense))
            .thenReturn(Arrays.asList(testTicket));

        // Act
        List<Ticket> result = ticketService.findByDriverLicense(driverLicense);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driverLicense, result.get(0).getVehicle().getDriverLicenseNumber());
        verify(ticketRepository).findByVehicle_DriverLicenseNumber(driverLicense);
    }

    @Test
    void findByTicketNumber_ShouldReturnTicket_WhenExists() {
        // Arrange
        String ticketNumber = "T123456789";
        when(ticketRepository.findById(ticketNumber))
            .thenReturn(Optional.of(testTicket));

        // Act
        Optional<Ticket> result = ticketService.findByTicketNumber(ticketNumber);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(ticketNumber, result.get().getTicketNumber());
        verify(ticketRepository).findById(ticketNumber);
    }

    @Test
    void findByTicketNumber_ShouldReturnEmpty_WhenNotExists() {
        // Arrange
        String ticketNumber = "NONEXISTENT";
        when(ticketRepository.findById(ticketNumber))
            .thenReturn(Optional.empty());

        // Act
        Optional<Ticket> result = ticketService.findByTicketNumber(ticketNumber);

        // Assert
        assertTrue(result.isEmpty());
        verify(ticketRepository).findById(ticketNumber);
    }
} 