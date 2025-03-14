package com.bookingflight.demo.repository;


import com.bookingflight.demo.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    boolean existsByAirportName(String airportName);
//    boolean existsByAirportCode( String airportCode);
}