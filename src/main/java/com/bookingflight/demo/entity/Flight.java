package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @Column(name = "flight_Code", length = 20)
    private String flightCode;

    @Column(name = "ticket_Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    @ManyToOne
    @JoinColumn(name = "departure_Airport_Code", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_Airport_Code", nullable = false)
    private Airport arrivalAirport;

    @Column(name = "departure_Date_Time", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "flight_Duration", nullable = false)
    private Integer flightDuration; // Flight duration in minutes

    @OneToMany(mappedBy = "flight")
    private List<FlightTransitAirport> transitAirports;

    @OneToMany(mappedBy = "flight")
    private List<FlightSeatClass> seatClasses;

    @OneToMany(mappedBy = "flight")
    private List<FlightTicket> flightTickets;
}