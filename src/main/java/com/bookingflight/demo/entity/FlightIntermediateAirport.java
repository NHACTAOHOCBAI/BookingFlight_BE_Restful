package com.bookingflight.demo.entity;

import com.bookingflight.demo.dto.request.FlightRequest;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class FlightIntermediateAirport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "flight_code", nullable = false)
    Flight flight;

    @ManyToOne
    @JoinColumn(name = "airport_code", nullable = false)
    Airport airport;

    @NotNull
    Integer stopoverDuration;

    String note;
}
