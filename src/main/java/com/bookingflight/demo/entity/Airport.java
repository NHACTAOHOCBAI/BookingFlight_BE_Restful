package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @NotNull
    String airportName;

    @NotNull
    String location;

    // @OneToMany(mappedBy = "departureAirport")
    // Set<Flight> departingFlights;

    // @OneToMany(mappedBy = "arrivalAirport")
    // Set<Flight> arrivingFlights;

    // @OneToMany(mappedBy = "airport")
    // Set<FlightIntermediateAirport> flightStopOvers;
}