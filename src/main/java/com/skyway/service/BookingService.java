package com.skyway.service;

import com.skyway.dto.BookingDTO;
import com.skyway.dto.BookingRequestDTO;
import com.skyway.dto.BookingResponseDTO;
import com.skyway.entity.Booking;
import com.skyway.util.BookingStatus;

import java.util.List;

public interface BookingService {

    public BookingDTO createBooking(BookingRequestDTO bookingRequestDTO);

    public BookingDTO getBookingInfo(Integer id);
    public List<BookingDTO> getBookingByFlightId(String flightId);
    public List<BookingDTO> getBookingByPassengerId(Integer passengerId);
    public List<BookingDTO> getBookingByFlightIdAndStatus(String flightId, BookingStatus status);
}
