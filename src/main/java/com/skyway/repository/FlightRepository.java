package com.skyway.repository;

import com.skyway.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {

    public Flight findById(String id);
    public List<Flight> findBySourceAndDestination(String source,String destination);
}
