package com.bookingflight.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookingflight.demo.entity.FlightSeatClass;
import com.bookingflight.demo.entity.SeatClass;

import jakarta.transaction.Transactional;

public interface FlightSeatClassRepository extends JpaRepository<FlightSeatClass, String> {

    List<FlightSeatClass> findBySeatClass(SeatClass seatClass);

    void deleteAllByFlightId(String flightId);

    @Modifying
    @Transactional
    @Query("UPDATE FlightSeatClass fsc " +
            "SET fsc.availableSeats = :quantity " +
            "WHERE fsc.flight.flightCode = :flightId " +
            "AND fsc.seatClass.id = :seatClassId")
    int updateSeatQuantity(String flightId, String seatClassId, int quantity);

}
