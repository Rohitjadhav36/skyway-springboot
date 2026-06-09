package com.skyway.controller;
import com.skyway.dto.FlightDTO;
import com.skyway.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @PostMapping("/add")
    public ResponseEntity<FlightDTO> addFlight(@RequestBody  FlightDTO flightDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightService.addFlight(flightDTO));
    }
    @GetMapping("/get-by/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable String id)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(flightService.getFlightById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<FlightDTO>> getAllFlights()
    {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> getFlightsBySourceAndDestination(@RequestParam("source") String source,
                                                                            @RequestParam("destination")String destination)
    {
        return ResponseEntity.ok(flightService.getFlightBySourceAndDestination(source,destination));
    }
}
