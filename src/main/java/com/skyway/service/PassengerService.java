package com.skyway.service;

import com.skyway.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {

    public PassengerDTO savePassenger(PassengerDTO passengerDTO);

    public List<PassengerDTO> getAllPassenger();
}

