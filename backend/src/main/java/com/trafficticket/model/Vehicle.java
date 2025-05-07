package com.trafficticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    private String licensePlateNumber;
    
    private String driverLicenseNumber;
    private String ownerLastName;
    private String ownerFirstName;
} 