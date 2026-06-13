package com.skyway.service.impl;

import com.skyway.dto.SeatDTO;
import com.skyway.entity.Flight;
import com.skyway.entity.Seat;
import com.skyway.repository.FlightRepository;
import com.skyway.repository.SeatRepository;
import com.skyway.service.FlightService;
import com.skyway.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Override
    public SeatDTO createSeats(SeatDTO seatDTO) {

        Seat createSeat = convertToEntity(seatDTO ,flightRepository.findById(seatDTO.getFlightId()));

        Seat addedSeat = seatRepository.save(createSeat);

        return convertToDTO(addedSeat);
    }
    public List<SeatDTO> getAvailableSeats(String flightId)
    {
      List<Seat> seats = seatRepository.findByFlightIdAndIsBookedFalse(flightId);
      List<SeatDTO> availableSeats = new ArrayList<>();
      for(Seat seat: seats)
      {
          availableSeats.add(convertToDTO(seat));
      }
      return availableSeats;
    }

    public List<SeatDTO> getSeatByFlightId(String flightId)
    {
        List<Seat> seats = seatRepository.findByFlightId(flightId);
        List<SeatDTO> availableSeats = new ArrayList<>();
        for(Seat seat: seats)
        {
            availableSeats.add(convertToDTO(seat));
        }
        return availableSeats;
    }
    public SeatDTO getSeatById(Integer id)
    {
        Seat seat = seatRepository.findById(id).get();
        return convertToDTO(seat);
    }
    private SeatDTO convertToDTO(Seat seat) {

        return SeatDTO.builder()
                .id(seat.getId())
                .seatNo(seat.getSeatNo())
                .seatClass(seat.getSeatClass())
                .isBooked(seat.getIsBooked())
                .flightId(seat.getFlight().getId())
                .build();
    }
    private Seat convertToEntity(SeatDTO dto, Flight flight) {

        return Seat.builder()

                .seatNo(dto.getSeatNo())
                .seatClass(dto.getSeatClass())
                .isBooked(dto.getIsBooked())
                .flight(flight)
                .build();
    }
}
