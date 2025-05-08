package com.trafficticket.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class TicketSearchRequest {
    private String licensePlate;
    private String driverLicense;

    @AssertTrue(message = "Either license plate or driver's license number must be provided")
    private boolean isEitherFieldProvided() {
        return (licensePlate != null && !licensePlate.trim().isEmpty()) ||
               (driverLicense != null && !driverLicense.trim().isEmpty());
    }
} 