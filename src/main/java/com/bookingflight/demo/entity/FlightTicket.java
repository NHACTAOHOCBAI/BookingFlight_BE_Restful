package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "Flight_Ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightTicket {

    @Id
    @Column(name = "ticket_code", length = 20)
    private String ticketCode;

    @ManyToOne
    @JoinColumn(name = "flight_Code", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "customer_Id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "seat_Class_Id", nullable = false)
    private SeatClass seatClass;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // Paid, Unpaid, Canceled
}