package com.skyway.service.impl;

import com.skyway.dto.PassengerDTO;
import com.skyway.entity.Passenger;
import com.skyway.repository.PassengerRepository;
import com.skyway.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO)
    {
        Passenger passenger = convertToEntity(passengerDTO);

        Passenger savedPassenger = passengerRepository.save(passenger);

        return convertToDTO(savedPassenger);
    }

    @Override
    public List<PassengerDTO> getAllPassenger()
    {
        List<Passenger> passengers = passengerRepository.findAll();

       return passengers.stream().map(passenger -> {
                PassengerDTO passengerDTO = convertToDTO(passenger);
               return  passengerDTO;
               }
                ).toList();
    }

    private Passenger convertToEntity(PassengerDTO passengerDTO)
    {
        return Passenger.builder().firstName(passengerDTO.getFirstName())
                .lastName(passengerDTO.getLastName())
                .age(passengerDTO.getAge())
                .gender(passengerDTO.getGender())
                .email(passengerDTO.getEmail())
                .mobileNo(passengerDTO.getMobileNo()).build();
    }

    private PassengerDTO convertToDTO(Passenger passenger)
    {
        return PassengerDTO.builder().id(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .age(passenger.getAge())
                .gender(passenger.getGender())
                .email(passenger.getEmail())
                .mobileNo(passenger.getMobileNo()).build();
    }
}
