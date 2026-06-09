package com.skyway.service.impl;
import com.skyway.dto.AirportDTO;
import com.skyway.entity.Airport;
import com.skyway.repository.AirportRepository;
import com.skyway.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Override
    public AirportDTO addAirport(AirportDTO airportDTO) {

        Airport airport = Airport.builder()
                .id(airportDTO.getId())
                .airportName(airportDTO.getAirportName())
                .airportCity(airportDTO.getAirportCity())
                .build();

        Airport savedAirport = airportRepository.save(airport);

        return AirportDTO.builder()
                .id(savedAirport.getId())
                .airportName(savedAirport.getAirportName())
                .airportCity(savedAirport.getAirportCity())
                .build();
    }
    public AirportDTO getAirportById(Integer id) {

        Airport airport = airportRepository.findById(id).get();

        return AirportDTO.builder()
                .id(airport.getId())
                .airportName(airport.getAirportName())
                .airportCity(airport.getAirportCity())
                .build();
    }
    public List<AirportDTO> getAllAirports() {

        List<Airport> airports = airportRepository.findAll();

        return airports.stream()
                .map(airport -> AirportDTO.builder()
                        .id(airport.getId())
                        .airportName(airport.getAirportName())
                        .airportCity(airport.getAirportCity())
                        .build())
                .toList();
    }
}
