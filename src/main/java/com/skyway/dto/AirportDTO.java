package com.skyway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AirportDTO {

    private Integer id;
    private String airportName;
    private String airportCity;
}
