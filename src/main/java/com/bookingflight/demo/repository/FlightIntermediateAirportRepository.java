package com.bookingflight.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingflight.demo.entity.FlightIntermediateAirport;

@Repository
public interface FlightIntermediateAirportRepository extends JpaRepository<FlightIntermediateAirport, String> {

    List<FlightIntermediateAirport> findByFlight_FlightCode(String flightCode);

}
