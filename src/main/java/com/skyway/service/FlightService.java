package com.skyway.service;

import com.skyway.dto.FlightDTO;

import java.util.List;

public interface FlightService {

    public FlightDTO addFlight(FlightDTO flightDTO);
    public FlightDTO getFlightById(String id);
    public List<FlightDTO> getAllFlights();
    public List<FlightDTO> getFlightBySourceAndDestination(String source,String destination);
}
