package com.skyway.service.impl;

import com.skyway.dto.FlightDTO;
import com.skyway.dto.SeatDTO;
import com.skyway.entity.Flight;
import com.skyway.entity.Seat;
import com.skyway.repository.FlightRepository;
import com.skyway.repository.SeatRepository;
import com.skyway.service.FlightService;
import com.skyway.service.SeatService;
import com.skyway.util.FlightStatus;
import com.skyway.util.SeatClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Transactional
    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) {

        int economyClassRate = 5;
        int bussinessClassRate = 8;
        int firstClassRate = 14;

        Flight newFlight = convertToEntity(flightDTO);

        //Setting the Price of Seasts
        newFlight.setEconomyClassPrice(newFlight.getDistance() * economyClassRate);
        newFlight.setBusinessClassPrice(newFlight.getDistance() * bussinessClassRate);
        newFlight.setFirstClassPrice(newFlight.getDistance() * firstClassRate);

        Flight addedFlight = flightRepository.save(newFlight);

        int economyClassSeat = addedFlight.getTotalSeats()/3;
        int bussinessClassSeat = economyClassSeat;
        int firstClassSeat = addedFlight.getTotalSeats() - economyClassSeat - bussinessClassSeat ;

       // Saving Ecconomy Seats
        for (int i=1 ; i <= economyClassSeat ; i++)
        {
            Seat seat = Seat.builder()
                    .seatNo("E"+ i)
                    .isBooked(false)
                    .seatClass(SeatClass.ECONOMY)
                    .flight(addedFlight)
                    .build();

            seatRepository.save(seat);
        }

        // Saving Bussiness Seats
        for (int i=1 ; i <= bussinessClassSeat ; i++)
        {
            Seat seat = Seat.builder()
                    .seatNo("B"+ i)
                    .isBooked(false)
                    .seatClass(SeatClass.BUSINESS)
                    .flight(addedFlight)
                    .build();

            seatRepository.save(seat);
        }

        // Saving First Class Seats
        for (int i = 1 ; i <= firstClassSeat ; i++)
        {
            Seat seat = Seat.builder()
                    .seatNo("F"+ i)
                    .isBooked(false)
                    .seatClass(SeatClass.FIRST)
                    .flight(addedFlight)
                    .build();

            seatRepository.save(seat);
        }

        return convertToDTO(addedFlight);
    }
    public FlightDTO getFlightById(String id)
    {
        Flight flight = flightRepository.findById(id);

       FlightDTO flightDTO = convertToDTO(flight);

       return flightDTO;
    }

    public List<FlightDTO> getAllFlights()
    {
        List<Flight> flights = flightRepository.findAll();

        return flights.stream().map(flight -> {
                FlightDTO flightDTO = convertToDTO(flight);
        return flightDTO;
    }).toList();
    }

    public List<FlightDTO> getFlightBySourceAndDestination(String source,String destination)
    {
        List<Flight> flights = flightRepository.findBySourceAndDestination(source,destination);
        return flights.stream().map(flight -> {
            FlightDTO flightDTO = convertToDTO(flight);
            return flightDTO;
        }).toList();

    }

    @Transactional
    @Override
    public void deleteFlightById(String id) {
        flightRepository.deleteById(id);
    }
    @Override
    public List<FlightDTO> getFlights() {

        Pageable pageable = PageRequest.of(0, 10);

        List<Flight> flights =
                flightRepository.findAll(pageable)
                        .getContent();

        return flights.stream()
                .map(this::convertToDTO)
                .toList();
    }
    private FlightDTO convertToDTO(Flight flight) {

       return FlightDTO.builder().id(flight.getId())
               .source(flight.getSource())
               .destination(flight.getDestination())
               .date(flight.getDate())
               .departureTime(flight.getDepartureTime())
               .arrivalTime(flight.getArrivalTime())
               .status(FlightStatus.SCHEDULED)
               .totalSeats(flight.getTotalSeats())
               .distance(flight.getDistance())
               .economyClassPrice(flight.getEconomyClassPrice())
               .businessClassPrice(flight.getBusinessClassPrice())
               .firstClassPrice(flight.getFirstClassPrice())
               .build();

    }

    private Flight convertToEntity(FlightDTO flightDTO) {

        return Flight.builder().id(flightDTO.getId())
                .source(flightDTO.getSource())
                .destination(flightDTO.getDestination())
                .date(flightDTO.getDate())
                .departureTime(flightDTO.getDepartureTime())
                .arrivalTime(flightDTO.getArrivalTime())
                .status(FlightStatus.SCHEDULED)
                .totalSeats(flightDTO.getTotalSeats())
                .distance(flightDTO.getDistance())
                .build();

    }
}

