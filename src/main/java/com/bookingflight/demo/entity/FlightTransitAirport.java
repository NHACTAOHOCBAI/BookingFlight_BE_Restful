package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Flight_Transit_Airport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightTransitAirport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "flight_Code", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "transit_Airport_Code", nullable = false)
    private Airport transitAirport;

    @Column(name = "stop_Duration", nullable = false)
    private Integer stopDuration; // in minutes

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}