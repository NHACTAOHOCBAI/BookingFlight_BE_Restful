package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Seat_Class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_Name", length = 50, nullable = false)
    private String className;

    @Column(name = "price_Percentage", nullable = false)
    private Float pricePercentage; // Price factor compared to base ticket price

    @OneToMany(mappedBy = "seatClass")
    private List<FlightSeatClass> flightSeatClasses;

    @OneToMany(mappedBy = "seatClass")
    private List<FlightTicket> flightTickets;
}