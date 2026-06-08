package com.skyway.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class FlightDTO {
    private String id;
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String status;
}
