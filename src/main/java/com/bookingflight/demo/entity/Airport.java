package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Airport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @Column(name = "airport_code", length = 10)
    private String airportCode;

    @Column(name = "airport_name", length = 100, nullable = false)
    private String airportName;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;

    @OneToMany(mappedBy = "transitAirport")
    private List<FlightTransitAirport> transitFlights;
}