package com.skyway.dto;
import com.skyway.util.SeatClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDTO {

    private String flightId;
    private String seatNo;
    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;
    private Boolean isBooked;
}
