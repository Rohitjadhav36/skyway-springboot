package com.skyway.service.impl;

import com.skyway.dto.FlightDTO;
import com.skyway.entity.Flight;
import com.skyway.repository.FlightRepository;
import com.skyway.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) {

        Flight newFlight = Flight.builder()
                .id(flightDTO.getId())
                .source(flightDTO.getSource())
                .destination(flightDTO.getDestination())
                .date(flightDTO.getDate())
                .departureTime(flightDTO.getDepartureTime())
                .arrivalTime(flightDTO.getArrivalTime())
                .status(flightDTO.getStatus())
                .build();

        Flight addedFlight = flightRepository.save(newFlight);

        return FlightDTO.builder()
                .id(addedFlight.getId())
                .source(addedFlight.getSource())
                .destination(addedFlight.getDestination())
                .date(addedFlight.getDate())
                .departureTime(addedFlight.getDepartureTime())
                .arrivalTime(addedFlight.getArrivalTime())
                .status(addedFlight.getStatus())
                .build();
    }
    public FlightDTO getFlightById(String id)
    {
        Flight flight = flightRepository.findById(id);

       FlightDTO flightDTO = FlightDTO.builder().id(flight.getId()).source(flight.getSource())
               .destination(flight.getDestination()).date(flight.getDate()).departureTime(flight.getDepartureTime())
               .arrivalTime(flight.getArrivalTime()).status(flight.getStatus()).build();

       return flightDTO;
    }

    public List<FlightDTO> getAllFlights()
    {
        List<Flight> flights = flightRepository.findAll();

        return flights.stream().map(flight -> {
                FlightDTO flightDTO = FlightDTO.builder().id(flight.getId()).source(flight.getSource())
                .destination(flight.getDestination()).date(flight.getDate()).departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime()).status(flight.getStatus()).build();
        return flightDTO;
    }).toList();
    }

    public List<FlightDTO> getFlightBySourceAndDestination(String source,String destination)
    {
        List<Flight> flights = flightRepository.findBySourceAndDestination(source,destination);
        return flights.stream().map(flight -> {
            FlightDTO flightDTO = FlightDTO.builder().id(flight.getId()).source(flight.getSource())
                    .destination(flight.getDestination()).date(flight.getDate()).departureTime(flight.getDepartureTime())
                    .arrivalTime(flight.getArrivalTime()).status(flight.getStatus()).build();
            return flightDTO;
        }).toList();

    }
}

