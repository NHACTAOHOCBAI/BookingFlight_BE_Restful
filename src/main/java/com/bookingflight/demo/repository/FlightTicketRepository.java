package com.bookingflight.demo.repository;


import com.bookingflight.demo.entity.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, String> {

}
