package com.skyway.repository;

import com.skyway.entity.Booking;
import com.skyway.util.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    public List<Booking> findByFlightId(String flightId);

    public List<Booking> findByPassengerId(Integer passengerId);

   public List<Booking> findByFlightIdAndStatus(String flightId, BookingStatus status);


}
