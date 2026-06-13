package com.skyway.controller;

import com.skyway.dto.PassengerDTO;
import com.skyway.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @PostMapping("/save")
    public ResponseEntity<PassengerDTO> savePassenger(PassengerDTO passengerDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(passengerService.savePassenger(passengerDTO));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<PassengerDTO>> getAllPassenger()
    {
        return ResponseEntity.ok(passengerService.getAllPassenger());
    }
}
