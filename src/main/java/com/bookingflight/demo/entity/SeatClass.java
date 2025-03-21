package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SeatClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @NotNull
    private String className;

    @NotNull
    private Float priceMultiplier;

    // @OneToMany(mappedBy = "seatClass")
    // private Set<FlightTicket> tickets;

    // @OneToMany(mappedBy = "seatClass")
    // private Set<FlightSeatClass> flightAssignments;
}