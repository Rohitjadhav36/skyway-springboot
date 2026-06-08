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
        Airport airport = new Airport();
        airport.setId(airportDTO.getId());
        airport.setAirportName(airportDTO.getAirportName());
        airport.setAirportCity(airportDTO.getAirportCity());

        Airport savedAirport = airportRepository.save(airport);

        AirportDTO responseDTO = new AirportDTO();
        responseDTO.setId(savedAirport.getId());
        responseDTO.setAirportName(savedAirport.getAirportName());
        responseDTO.setAirportCity(savedAirport.getAirportCity());

        return responseDTO;
    }
    public AirportDTO getAirportById(Integer id)
    {
        Airport airport=airportRepository.findById(id).get();

        AirportDTO airportDTO=new AirportDTO();
        airportDTO.setId(airport.getId());
        airportDTO.setAirportName(airport.getAirportName());
        airportDTO.setAirportCity(airport.getAirportCity());

        return airportDTO;
    }
    public List<AirportDTO> getAllAirports()
    {
        List<Airport> airports = airportRepository.findAll();

        return airports.stream()
                .map(airport -> {
                    AirportDTO dto = new AirportDTO();
                    dto.setId(airport.getId());
                    dto.setAirportName(airport.getAirportName());
                    dto.setAirportCity(airport.getAirportCity());
                    return dto;
                })
                .toList();
    }
}
