package com.skyway.dto;

import com.skyway.entity.Flight;
import com.skyway.entity.Passenger;
import com.skyway.entity.Seat;
import com.skyway.util.BookingStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingDTO {

    private Integer id;

    private FlightDTO flight;

    private PassengerDTO passenger;

    private SeatDTO seat;

    private BookingStatus status;

    private LocalDate bookingDate;
}

