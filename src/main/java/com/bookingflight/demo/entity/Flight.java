package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
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
    @JoinColumn(name = "departureAirportId", nullable = false)
    Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrivalAirportId", nullable = false)
    Airport arrivalAirport;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    Date departureTime;

    @NotNull
    Integer flightDuration; // Flight duration in minutes

}