package com.skyway.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequestDTO {

    private String flightId;

    private Integer seatId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String email;

    private String mobile;

}
