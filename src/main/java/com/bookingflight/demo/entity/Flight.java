package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;
import java.math.BigDecimal;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String flightCode;

    @NotNull
    BigDecimal basePrice;

    @ManyToOne
    @JoinColumn(name = "departure_airport_code", nullable = false)
    Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_code", nullable = false)
    Airport arrivalAirport;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    Date departureTime;

    @NotNull
    Integer flightDuration; // Flight duration in minutes

    @OneToMany(mappedBy = "flight")
    Set<FlightTicket> tickets;

    @OneToMany(mappedBy = "flight")
    Set<FlightIntermediateAirport> flightIntermediateAirports;

    @OneToMany(mappedBy = "flight")
    Set<FlightSeatClass> flightSeatClasses;

}