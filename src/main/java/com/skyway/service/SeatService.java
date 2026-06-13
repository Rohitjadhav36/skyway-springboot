package com.skyway.service;

import com.skyway.dto.SeatDTO;

import java.util.List;

public interface SeatService {

    public SeatDTO createSeats(SeatDTO seatDTO);

    public List<SeatDTO> getAvailableSeats(String flightId);

    public SeatDTO getSeatById(Integer id);

    public List<SeatDTO> getSeatByFlightId(String flightId);


}
