package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @NotNull
    String fullName;

    @NotNull
    Boolean isOver14YearsOld; // TRUE if over 14 years old, FALSE if under 14 years old

    @NotNull
    String idCard;

    String phoneNumber;

    String email;

    @OneToMany(mappedBy = "customer")
    Set<FlightTicket> tickets;
}