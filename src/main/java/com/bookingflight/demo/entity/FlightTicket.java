package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class FlightTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String ticketCode;

    @NotNull
    BigDecimal price;

    @NotNull
    String status; // PaidStatus: Paid, Unpaid, Canceled

    @ManyToOne
    @JoinColumn(name = "flight_code", nullable = false)
    Flight flight;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "seat_class_id", nullable = false)
    SeatClass seatClass;

}