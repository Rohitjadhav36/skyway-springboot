package com.skyway.controller;
import com.skyway.dto.AirportDTO;
import com.skyway.entity.Airport;
import com.skyway.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @PostMapping("/add")
    public ResponseEntity<AirportDTO> addAirport(@RequestBody AirportDTO airportDTO)
    {
        System.out.println("controller");
        AirportDTO savedAirport = airportService.addAirport(airportDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAirport);
    }

    @GetMapping("/get-by/{id}")
    public ResponseEntity<AirportDTO> getAirportById(@PathVariable Integer id)
        {
            AirportDTO savedAirport = airportService.getAirportById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(savedAirport);
        }
        @GetMapping("/get-all")
    public ResponseEntity<List<AirportDTO>> getAllAirports()
        {
            List<AirportDTO> allAirports = airportService.getAllAirports();
            return ResponseEntity.ok(allAirports);
        }

}
