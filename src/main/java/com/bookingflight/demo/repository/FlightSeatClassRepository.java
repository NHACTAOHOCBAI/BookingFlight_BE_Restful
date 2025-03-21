package com.bookingflight.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bookingflight.demo.entity.FlightSeatClass;
import com.bookingflight.demo.entity.SeatClass;

public interface FlightSeatClassRepository extends JpaRepository<FlightSeatClass, String> {
    List<FlightSeatClass> findBySeatClass(SeatClass seatClass);
}
