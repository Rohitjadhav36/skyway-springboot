package com.skyway.entity;

import com.skyway.util.SeatClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    private Boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
