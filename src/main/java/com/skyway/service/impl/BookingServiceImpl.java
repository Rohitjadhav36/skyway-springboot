package com.skyway.service.impl;
import com.skyway.dto.*;
import com.skyway.entity.Booking;
import com.skyway.entity.Flight;
import com.skyway.entity.Passenger;
import com.skyway.entity.Seat;
import com.skyway.repository.BookingRepository;
import com.skyway.repository.FlightRepository;
import com.skyway.repository.PassengerRepository;
import com.skyway.repository.SeatRepository;
import com.skyway.service.BookingService;
import com.skyway.util.BookingStatus;
import com.skyway.util.FlightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingRequestDTO bookingRequestDTO) {

        Passenger passenger = Passenger.builder()
                        .firstName(bookingRequestDTO.getFirstName())
                        .lastName(bookingRequestDTO.getLastName())
                        .age(bookingRequestDTO.getAge())
                        .gender(bookingRequestDTO.getGender())
                        .email(bookingRequestDTO.getEmail())
                        .mobileNo(bookingRequestDTO.getMobile())
                        .build();
       //get Flight
        Flight flight = flightRepository.findById(bookingRequestDTO.getFlightId());

        //get seat and update
        Seat seat = seatRepository.findById(bookingRequestDTO.getSeatId()).get();
        seat.setIsBooked(true);
        Seat bookedSeat = seatRepository.save(seat);

        //save passenger
        Passenger savedPassenger = passengerRepository.save(passenger);

        Booking booking =
                Booking.builder()
                        .flight(flight)
                        .seat(bookedSeat)
                        .passenger(savedPassenger)
                        .status(BookingStatus.CONFIRMED)
                        .bookingDate(LocalDate.now())
                        .build();


        Booking savedBooking = bookingRepository.save(booking);

        return convertToDTO(savedBooking);
    }
    public BookingDTO getBookingInfo(Integer id)
    {
        Booking booking = bookingRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Booking Not Found"));

        return convertToDTO(booking);
    }
    @Override
    public List<BookingDTO> getBookingByFlightId(String flightId) {
        List<Booking> bookings = bookingRepository.findByFlightId(flightId);

        return bookings.stream().map(booking ->
        {
           BookingDTO bookingDTO = convertToDTO(booking);
           return bookingDTO;
        }).toList();
    }

    @Override
    public List<BookingDTO> getBookingByPassengerId(Integer passengerId) {
        List<Booking> bookings = bookingRepository.findByPassengerId(passengerId);

        return bookings.stream().map(booking ->
        {
            BookingDTO bookingDTO = convertToDTO(booking);
            return bookingDTO;
        }).toList();
    }

    @Override
    public List<BookingDTO> getBookingByFlightIdAndStatus(String flightId, BookingStatus status) {

        List<Booking> bookings = bookingRepository.findByFlightIdAndStatus(flightId,status);

        return bookings.stream().map(booking ->
        {
            BookingDTO bookingDTO = convertToDTO(booking);
            return bookingDTO;
        }).toList();
    }

    private BookingDTO convertToDTO(Booking booking)
    {
        return BookingDTO.builder().id(booking.getId())
                .flight(convertToFlightDTO(booking.getFlight()))
                .passenger(convertToPassengerDTO(booking.getPassenger()))
                .seat(convertToSeatDTO(booking.getSeat()))
                .bookingDate(booking.getBookingDate())
                .status(booking.getStatus())
                .build();
    }
    private Booking convertToEntity(BookingDTO bookingDTO)
    {
        return Booking.builder().flight(convertToFlightEntity(bookingDTO.getFlight()))
                .passenger(convertToPassengerEntity(bookingDTO.getPassenger()))
                .seat(convertToSeatEntity(bookingDTO.getSeat(),convertToFlightEntity(bookingDTO.getFlight())))
                .status(bookingDTO.getStatus()).build();
    }

    private FlightDTO convertToFlightDTO(Flight flight)
    {
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

    private SeatDTO convertToSeatDTO(Seat seat)
    {
        return SeatDTO.builder()
                .id(seat.getId())
                .seatNo(seat.getSeatNo())
                .seatClass(seat.getSeatClass())
                .isBooked(seat.getIsBooked())
                .flightId(seat.getFlight().getId())
                .build();
    }

    private PassengerDTO convertToPassengerDTO(Passenger passenger)
    {
        return PassengerDTO.builder().id(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .age(passenger.getAge())
                .gender(passenger.getGender())
                .email(passenger.getEmail())
                .mobileNo(passenger.getMobileNo()).build();
    }
    private Seat convertToSeatEntity(SeatDTO dto, Flight flight) {

        return Seat.builder()
                .seatNo(dto.getSeatNo())
                .seatClass(dto.getSeatClass())
                .isBooked(dto.getIsBooked())
                .flight(flight)
                .build();
    }
    private Passenger convertToPassengerEntity(PassengerDTO passengerDTO)
    {
        return Passenger.builder().firstName(passengerDTO.getFirstName())
                .lastName(passengerDTO.getLastName())
                .age(passengerDTO.getAge())
                .gender(passengerDTO.getGender())
                .email(passengerDTO.getEmail())
                .mobileNo(passengerDTO.getMobileNo()).build();
    }
    private Flight convertToFlightEntity(FlightDTO flightDTO) {

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
