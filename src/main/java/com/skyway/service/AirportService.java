package com.skyway.service;

import com.skyway.dto.AirportDTO;
import com.skyway.entity.Airport;

import java.util.List;

public interface AirportService {

    public AirportDTO addAirport(AirportDTO airportDTO);
    public AirportDTO getAirportById(Integer id);

    public List<AirportDTO> getAllAirports();

}
