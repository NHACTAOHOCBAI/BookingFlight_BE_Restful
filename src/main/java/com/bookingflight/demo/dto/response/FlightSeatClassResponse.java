package com.bookingflight.demo.dto.response;

import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.SeatClass;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightSeatClassResponse {
    String id;
    Flight flight;
    SeatClass seatClass;
    Integer quantity;
}
