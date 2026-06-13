package com.skyway.controller;
import com.skyway.dto.BookingDTO;
import com.skyway.dto.BookingRequestDTO;
import com.skyway.dto.BookingResponseDTO;
import com.skyway.service.BookingService;
import com.skyway.util.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/save")
    public ResponseEntity<BookingDTO> saveBooking(@RequestBody BookingRequestDTO bookingRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.createBooking(bookingRequestDTO));
    }

    @GetMapping("/get-by/flight/{flightId}")
    public ResponseEntity<List<BookingDTO>> getBookingByFlightId(@PathVariable String flightId)
    {
        return ResponseEntity.ok(bookingService.getBookingByFlightId(flightId));
    }

    @GetMapping("/get-by/passenger/{passengerId}")
    public ResponseEntity<List<BookingDTO>> getBookingByPassengerId(@PathVariable Integer passengerId)
    {
        return ResponseEntity.ok(bookingService.getBookingByPassengerId(passengerId));
    }

    @GetMapping("/get-by/flight-and-status")
    public ResponseEntity<List<BookingDTO>> getBookingByFlightIdAndStatus(@RequestParam String flightId,@RequestParam BookingStatus status)
    {
        return ResponseEntity.ok(bookingService.getBookingByFlightIdAndStatus(flightId , status));
    }

    @GetMapping("/get-info-by/{id}")
    public ResponseEntity<BookingDTO> getBookingInfo(@PathVariable Integer id)
    {
          return ResponseEntity.ok(bookingService.getBookingInfo(id));
    }
}
