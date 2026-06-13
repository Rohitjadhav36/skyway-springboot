package com.skyway.dto;

import com.skyway.util.SeatClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {

    private String flightId;

    private Integer seatId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String email;

    private String mobile;

}
