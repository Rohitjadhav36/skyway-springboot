package com.skyway.service;

import com.skyway.dto.FlightDTO;
import com.skyway.entity.Flight;

import java.util.List;

public interface FlightService {

    public FlightDTO addFlight(FlightDTO flightDTO);
    public FlightDTO getFlightById(String id);
    public List<FlightDTO> getAllFlights();
    public List<FlightDTO> getFlightBySourceAndDestination(String source,String destination);

    public void deleteFlightById(String id);

    public List<FlightDTO> getFlights();
}
