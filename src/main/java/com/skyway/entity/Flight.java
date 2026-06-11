package com.skyway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skyway.util.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
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


    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<Seat> seats;
}
