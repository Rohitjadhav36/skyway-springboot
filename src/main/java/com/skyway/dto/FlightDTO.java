package com.skyway.dto;

import com.skyway.util.FlightStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
public class FlightDTO {

    private String id;
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    @Enumerated(EnumType.STRING)
    private FlightStatus status;
    private Integer totalSeats;
    private Double distance;
    private Double economyClassPrice;
    private Double businessClassPrice;
    private Double firstClassPrice;
}
