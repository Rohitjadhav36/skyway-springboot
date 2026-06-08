package com.skyway.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Data
public class Airport {

    @Id
    private Integer id;
    private String airportName;
    private String airportCity;
}
