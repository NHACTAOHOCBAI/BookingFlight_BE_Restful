package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightIntermediateAirport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "flightId", nullable = false)
    Flight flight;

    @ManyToOne
    @JoinColumn(name = "airportId", nullable = false)
    Airport airport;

    @NotNull
    Integer stopoverDuration;

    String note;
}
