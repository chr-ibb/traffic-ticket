package com.trafficticket.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TicketSearchRequest {
    @NotBlank(message = "Either license plate or driver's license number must be provided")
    private String licensePlate;
    
    private String driverLicense;
} 