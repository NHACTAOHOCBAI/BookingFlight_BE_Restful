package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Flight_Seat_Class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSeatClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "flight_Code", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "seat_Class_Id", nullable = false)
    private SeatClass seatClass;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}